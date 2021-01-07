package com.cuiwz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuiwz.controller.vo.CinemaSavedReqVO;
import com.cuiwz.controller.vo.DescribeCinemaRespVO;
import com.cuiwz.dao.entity.MoocCinemaT;
import com.cuiwz.dao.mapper.MoocCinemaTMapper;
import com.cuiwz.exception.CommonServiceException;
import com.cuiwz.service.CinemaService;
import com.cuiwz.utils.ToolUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author cuiwz
 * @Date 2020/5/17 22:16
 */
@Service
public class CinemaServiceImpl implements CinemaService {

    @Resource
    private MoocCinemaTMapper cinemaTMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCinema(CinemaSavedReqVO reqVO) throws CommonServiceException {
        MoocCinemaT cinema = new MoocCinemaT();
        // 填写具体参数
        cinema.setCinemaName(reqVO.getCinemaName());
        cinema.setCinemaPhone(reqVO.getCinemaTele());
        cinema.setBrandId(ToolUtils.str2Int(reqVO.getBrandId()));
        cinema.setAreaId(ToolUtils.str2Int(reqVO.getAreaId()));
        cinema.setHallIds(reqVO.getHallTypeIds());
        cinema.setImgAddress(reqVO.getCinemaAddress());
        cinema.setCinemaAddress(reqVO.getCinemaImgAddress());
        cinema.setMinimumPrice(ToolUtils.str2Int(reqVO.getCinemaPrice()));
        // 对实体对象进行保存
        cinemaTMapper.insert(cinema);
    }

    @Override
    public IPage<DescribeCinemaRespVO> describeCinemas(int nowPage, int pageSize)
            throws CommonServiceException {
        // 查询实体对象，然后与表现层对象进行交互
//        Page<MoocCinemaT> page = new Page<>(nowPage, pageSize);
//        IPage<MoocCinemaT> moocCinemaTIPage = cinemaTMapper.selectPage(page, null);

        // moocCinemaTIPage内的分页参数与IPage<DescribeCinemaRespVO>一模一样
//        Page<DescribeCinemaRespVO> result = new Page<>();
//        moocCinemaTIPage.getRecords(); // set到IPage<DescribeCinemaRespVO> records方法里

        return cinemaTMapper.describeCinemas(new Page<>(nowPage, pageSize));
    }
}
