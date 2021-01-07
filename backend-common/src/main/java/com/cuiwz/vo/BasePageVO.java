package com.cuiwz.vo;

import com.cuiwz.exception.CommonServiceException;
import lombok.Data;

/**
 * @Author cuiwz
 * @Date 2020/5/16 10:50
 */
@Data
public class BasePageVO extends BaseRequestVO {

    private Integer nowPage = 1;
    private Integer pageSize = 10;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
