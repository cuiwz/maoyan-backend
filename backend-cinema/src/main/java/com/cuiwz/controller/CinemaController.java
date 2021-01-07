package com.cuiwz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cuiwz.controller.vo.CinemaSavedReqVO;
import com.cuiwz.controller.vo.DescribeCinemaRespVO;
import com.cuiwz.exception.CommonServiceException;
import com.cuiwz.service.CinemaService;
import com.cuiwz.vo.BasePageVO;
import com.cuiwz.vo.BaseResponseVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author cuiwz
 * @Date 2020/5/17 22:08
 */
@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Resource
    private CinemaService cinemaService;

    /**
     * 新增影院
     * @param cinemaSavedReqVO
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "/cinema:add", method = RequestMethod.POST)
    public BaseResponseVO saveCinema(@RequestBody CinemaSavedReqVO cinemaSavedReqVO)
            throws CommonServiceException {

        // 校验数据
        cinemaSavedReqVO.checkParam();

        cinemaService.saveCinema(cinemaSavedReqVO);

        return BaseResponseVO.success();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public BaseResponseVO describeCinemas(BasePageVO basePageVO)
            throws CommonServiceException {

        IPage<DescribeCinemaRespVO> page =
                cinemaService.describeCinemas(basePageVO.getNowPage(), basePageVO.getPageSize());

        // 调用封装的分页返回方法
        Map<String, Object> result = describePageResult(page, "cinemas");

        return BaseResponseVO.success(result);
    }

    /**
     * 获取分页对象的公共接口
     * @param page
     * @param title
     * @return
     */
    private Map<String, Object> describePageResult(IPage page, String title) {
        Map<String, Object> result = new HashMap<>();

        result.put("totalSize", page.getTotal());
        result.put("totalPages", page.getPages());
        result.put("pageSize", page.getSize());
        result.put("nowPage", page.getCurrent());

        result.put(title, page.getRecords());

        return result;
    }

}
