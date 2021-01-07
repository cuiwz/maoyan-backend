package com.cuiwz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuiwz.dao.entity.MoocBackendUserT;
import com.cuiwz.dao.mapper.MoocBackendUserTMapper;
import com.cuiwz.exception.CommonServiceException;
import com.cuiwz.service.UserService;
import com.cuiwz.utils.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author cuiwz
 * @Date 2020/5/15 20:37
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private MoocBackendUserTMapper userMapper;

    @Override
    public String checkUserLogin(String username, String password) throws CommonServiceException {
        // 根据用户名获取用户信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", username);

        MoocBackendUserT user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new CommonServiceException(404, "用户名输入有误");
        }

        // 验证码是否正确，需要做MD5转换
        String encrypt = MD5Utils.encrypt(password);
        if (!encrypt.equals(user.getUserPwd()))
            throw new CommonServiceException(500, "用户密码输入有误");
        else
            return String.valueOf(user.getUuid());
    }

}
