package com.cuiwz.vo;

import com.cuiwz.exception.CommonServiceException;
import lombok.Data;

/**
 * @Author cuiwz
 * @Date 2020/5/15 16:50
 */
@Data
public class BaseResponseVO<T> {

    private Integer code;   // 业务编号
    private String message; // 异常信息
    private T data;         // 业务数据

    private BaseResponseVO() {}

    // 成功无数据
    public static BaseResponseVO success() {
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(200);
        response.setMessage("");
        return response;
    }

    // 成功有数据
    public static <T> BaseResponseVO success(T data) {
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(200);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    // 业务异常
    public static <T> BaseResponseVO serviceException(CommonServiceException e) {
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(e.getCode());
        response.setMessage(e.getMessage());
        return response;
    }

}
