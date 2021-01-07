package com.cuiwz.vo;

import com.cuiwz.exception.CommonServiceException;

/**
 * @Author cuiwz
 * @Date 2020/5/15 19:28
 */
public abstract class BaseRequestVO {

    /**
     * 公共的参数验证方法
     */
    public abstract void checkParam() throws CommonServiceException;

}
