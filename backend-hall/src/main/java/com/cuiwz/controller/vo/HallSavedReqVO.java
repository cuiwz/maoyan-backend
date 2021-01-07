package com.cuiwz.controller.vo;

import com.cuiwz.exception.CommonServiceException;
import com.cuiwz.vo.BaseRequestVO;
import lombok.Data;

/**
 * @Author cuiwz
 * @Date 2020/5/19 11:25
 */
@Data
public class HallSavedReqVO extends BaseRequestVO {

    private String cinemaId;
    private String filmId;
    private String hallTypeId;
    private String beginTime;
    private String endTime;
    private String filmPrice;
    private String hallName;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
