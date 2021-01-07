package com.cuiwz.controller.vo;

import com.cuiwz.exception.CommonServiceException;
import com.cuiwz.vo.BasePageVO;
import lombok.Data;

/**
 * @Author cuiwz
 * @Date 2020/5/19 11:09
 */
@Data
public class HallsReqVO extends BasePageVO {

    private String cinemaId;

    @Override
    public void checkParam() throws CommonServiceException {
        super.checkParam();
    }
}
