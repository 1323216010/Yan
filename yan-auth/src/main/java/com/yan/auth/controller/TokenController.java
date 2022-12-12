package com.yan.auth.controller;

import javax.servlet.http.HttpServletRequest;
import com.yan.common.core.constant.SecurityConstants;
import com.yan.common.core.utils.ServletUtils;
import com.yan.common.core.utils.ip.AddressUtils;
import com.yan.common.core.utils.ip.IpUtils;
import com.yan.system.api.RemoteUserService;
import com.yan.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.yan.auth.form.LoginBody;
import com.yan.auth.form.RegisterBody;
import com.yan.auth.service.SysLoginService;
import com.yan.common.core.domain.R;
import com.yan.common.core.utils.JwtUtils;
import com.yan.common.core.utils.StringUtils;
import com.yan.common.security.auth.AuthUtil;
import com.yan.common.security.service.TokenService;
import com.yan.common.security.utils.SecurityUtils;
import com.yan.system.api.model.LoginUser;

import java.util.HashMap;
import java.util.Map;

/**
 * token 控制
 * 
 * @author yan
 */
@RestController
public class TokenController
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @Autowired
    private RemoteUserService remoteUserService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form, HttpServletRequest httpServletRequest)
    {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
        //设置用户IP
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userInfo.getSysUser().getUserId());
        sysUser.setUserName(userInfo.getSysUser().getUserName());
        sysUser.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        sysUser.setLoginAddress(AddressUtils.getRealAddressByIP(IpUtils.getIpAddr(ServletUtils.getRequest())));
        remoteUserService.setUserIp(sysUser, SecurityConstants.INNER);

        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request)
    {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    @PostMapping("register")
    public R<?> register(@RequestBody RegisterBody registerBody)
    {
        // 用户注册
        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
        return R.ok();
    }

    @PostMapping("singleSignOn/getToken")
    public Map<String, Object> getToken() {
        LoginUser userInfo = sysLoginService.login("admin", "admin123");
        Map<String, Object> map = tokenService.createToken(userInfo);
        return map;
    }
}
