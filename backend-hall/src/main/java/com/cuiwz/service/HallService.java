package com.cuiwz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cuiwz.controller.vo.HallSavedReqVO;
import com.cuiwz.controller.vo.HallsRepsVO;
import com.cuiwz.controller.vo.HallsReqVO;
import com.cuiwz.exception.CommonServiceException;

/**
 * @Author cuiwz
 * @Date 2020/5/19 10:44
 */
public interface HallService {

    /**
     * 获取全部播放厅信息
     * @param hallsReqVO
     * @return
     * @throws CommonServiceException
     */
    IPage<HallsRepsVO> describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException;

    /**
     * 保存影厅信息
     * @param hallSavedReqVO
     * @throws CommonServiceException
     */
    void saveHall(HallSavedReqVO hallSavedReqVO) throws CommonServiceException;

}
