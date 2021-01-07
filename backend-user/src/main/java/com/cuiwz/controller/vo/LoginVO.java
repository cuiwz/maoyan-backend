package com.cuiwz.controller.vo;

import com.cuiwz.exception.CommonServiceException;
import com.cuiwz.utils.ToolUtils;
import com.cuiwz.vo.BaseRequestVO;
import lombok.Data;

/**
 * @Author cuiwz
 * @Date 2020/5/15 19:26
 */
@Data
public class LoginVO extends BaseRequestVO {

    private String username;
    private String password;

    @Override
    public void checkParam() throws CommonServiceException {
        if (ToolUtils.strIsNull(username) || ToolUtils.strIsNull(password))
            throw new CommonServiceException(404, "用户名或者密码不能为空");
    }
}
