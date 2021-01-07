package com.cuiwz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cuiwz.controller.vo.CinemaSavedReqVO;
import com.cuiwz.controller.vo.DescribeCinemaRespVO;
import com.cuiwz.exception.CommonServiceException;

/**
 * @Author cuiwz
 * @Date 2020/5/17 22:15
 */
public interface CinemaService {

    /**
     * 保存影院信息
     * @param reqVO
     */
    void saveCinema(CinemaSavedReqVO reqVO) throws CommonServiceException;

    /**
     * 获取影院列表
     * @return
     * @throws CommonServiceException
     */
    IPage<DescribeCinemaRespVO> describeCinemas(int nowPage, int pageSize) throws CommonServiceException;

}
