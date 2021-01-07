package com.cuiwz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cuiwz.controller.vo.HallSavedReqVO;
import com.cuiwz.controller.vo.HallsRepsVO;
import com.cuiwz.controller.vo.HallsReqVO;
import com.cuiwz.exception.CommonServiceException;
import com.cuiwz.service.HallService;
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
 * @Date 2020/5/19 10:44
 */
@RestController
@RequestMapping("/halls")
public class HallController {

    @Resource
    private HallService hallService;

    /**
     * 获取影厅信息
     * @param hallsReqVO
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public BaseResponseVO describeHalls(HallsReqVO hallsReqVO)
            throws CommonServiceException {
        // 校验参数
        hallsReqVO.checkParam();

        IPage<HallsRepsVO> page = hallService.describeHalls(hallsReqVO);

        Map<String, Object> result = describePageResult(page, "halls");

        return BaseResponseVO.success(result);
    }

    /**
     * 新增放映厅
     * @param hallSavedReqVO
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "hall:add", method = RequestMethod.POST)
    public BaseResponseVO saveHall(@RequestBody HallSavedReqVO hallSavedReqVO)
            throws CommonServiceException {

        // 校验参数
        hallSavedReqVO.checkParam();

        hallService.saveHall(hallSavedReqVO);

        return BaseResponseVO.success();
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
