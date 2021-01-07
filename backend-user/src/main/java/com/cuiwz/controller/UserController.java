package com.cuiwz.controller;

import com.cuiwz.controller.vo.LoginVO;
import com.cuiwz.exception.CommonServiceException;
import com.cuiwz.service.UserService;
import com.cuiwz.utils.JwtTokenUtils;
import com.cuiwz.vo.BaseResponseVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author cuiwz
 * @Date 2020/5/14 19:19
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponseVO login(@RequestBody LoginVO loginVO) throws CommonServiceException {
        // 数据验证
        loginVO.checkParam();

        // 验证用户名和密码是否正确
        String userId = userService.checkUserLogin(loginVO.getUsername(), loginVO.getPassword());

        JwtTokenUtils jwtTokenUtil = new JwtTokenUtils();
        String randomKey = jwtTokenUtil.getRandomKey();
        String token = jwtTokenUtil.generateToken(userId, randomKey);

        // randomKey token
        Map<String, String> result = new HashMap<>();
        result.put("randomKey", randomKey);
        result.put("token", token);

        return BaseResponseVO.success(result);
    }

}
