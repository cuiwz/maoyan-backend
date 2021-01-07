package com.cuiwz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuiwz.controller.vo.HallSavedReqVO;
import com.cuiwz.controller.vo.HallsRepsVO;
import com.cuiwz.controller.vo.HallsReqVO;
import com.cuiwz.dao.entity.MoocFieldT;
import com.cuiwz.dao.entity.MoocHallFilmInfoT;
import com.cuiwz.dao.mapper.MoocFieldTMapper;
import com.cuiwz.dao.mapper.MoocHallFilmInfoTMapper;
import com.cuiwz.exception.CommonServiceException;
import com.cuiwz.service.HallService;
import com.cuiwz.utils.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author cuiwz
 * @Date 2020/5/19 10:45
 */
@Service
public class HallServiceImpl implements HallService {

    @Resource
    private MoocFieldTMapper fieldTMapper;

    @Resource
    private MoocHallFilmInfoTMapper filmInfoTMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient eurekaClient;

    @Override
    public IPage<HallsRepsVO> describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException {

        Page<HallsReqVO> page = new Page<>(hallsReqVO.getNowPage(), hallsReqVO.getPageSize());

        QueryWrapper queryWrapper = new QueryWrapper();
        if (ToolUtils.strIsNotNul(hallsReqVO.getCinemaId())) {
            queryWrapper.eq("cinema_id", hallsReqVO.getCinemaId());
        }

        IPage<HallsRepsVO> result = fieldTMapper.describeHalls(page, queryWrapper);

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveHall(HallSavedReqVO reqVO) throws CommonServiceException {
        // 播放厅的列表数据
        MoocFieldT field = new MoocFieldT();

        field.setCinemaId(ToolUtils.str2Int(reqVO.getCinemaId()));
        field.setFilmId(ToolUtils.str2Int(reqVO.getFilmId()));
        field.setBeginTime(reqVO.getBeginTime());
        field.setEndTime(reqVO.getEndTime());
        field.setHallId(ToolUtils.str2Int(reqVO.getHallTypeId()));
        field.setHallName(reqVO.getHallName());
        field.setPrice(ToolUtils.str2Int(reqVO.getFilmPrice()));

        fieldTMapper.insert(field);
        // 播放厅对应的影片数据，影片冗余数据， 缓存里有一份
        MoocHallFilmInfoT hallFilmInfo = describeFilmInfo(reqVO.getFilmId());

        filmInfoTMapper.insert(hallFilmInfo);
    }

    private MoocHallFilmInfoT describeFilmInfo(String filmId) throws CommonServiceException {
        // GET REGISTER
        ServiceInstance choose = eurekaClient.choose("film-service");
        // 组织调用参数
        String hostname = choose.getHost();
        int port = choose.getPort();

        String uri = "/films/" + filmId;
        String url = "http://" + hostname + ":" + port + uri;

        // 通过restTemplate调用影片服务
        JSONObject baseResponseVO = restTemplate.getForObject(url, JSONObject.class);

        // 解析返回值
        JSONObject dataJson = baseResponseVO.getJSONObject("data");

        // 组织参数
        MoocHallFilmInfoT hallFilmInfo = new MoocHallFilmInfoT();
        hallFilmInfo.setFilmId(dataJson.getIntValue("filmId"));
        hallFilmInfo.setFilmName(dataJson.getString("filmName"));
        hallFilmInfo.setFilmLength(dataJson.getString("filmLength"));
        hallFilmInfo.setFilmCats(dataJson.getString("filmCats"));
        hallFilmInfo.setActors(dataJson.getString("actors"));
        hallFilmInfo.setImgAddress(dataJson.getString("imgAddress"));

        return hallFilmInfo;
    }

}
