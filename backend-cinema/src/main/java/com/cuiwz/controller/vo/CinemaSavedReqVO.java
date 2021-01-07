package com.cuiwz.controller.vo;

import com.cuiwz.exception.CommonServiceException;
import com.cuiwz.vo.BaseRequestVO;
import lombok.Data;

/**
 * @Author cuiwz
 * @Date 2020/5/17 22:12
 */
@Data
public class CinemaSavedReqVO extends BaseRequestVO {

    private String brandId;
    private String areaId;
    private String hallTypeIds;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaTele;
    private String cinemaImgAddress;
    private String cinemaPrice;

    @Override
    public void checkParam() throws CommonServiceException {

    }

}
