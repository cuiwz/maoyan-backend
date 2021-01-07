package com.cuiwz.service;

import com.cuiwz.exception.CommonServiceException;

/**
 * @Author cuiwz
 * @Date 2020/5/15 20:36
 */
public interface UserService {

    String checkUserLogin(String username, String password) throws CommonServiceException;

}
