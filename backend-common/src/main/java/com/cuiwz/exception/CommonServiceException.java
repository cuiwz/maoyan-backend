package com.cuiwz.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author cuiwz
 * @Date 2020/5/15 19:30
 */
@Data
@AllArgsConstructor
public class CommonServiceException extends Exception {

    private Integer code;
    private String message;

}
