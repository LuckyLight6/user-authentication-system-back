package club.luckylight.controller;

import club.luckylight.dto.UseablePermissionDto;
import club.luckylight.model.User;
import club.luckylight.service.AuthService;
import club.luckylight.util.JWTUtils;
import club.luckylight.util.NetworkUtil;
import club.luckylight.util.ValidationUtils;
import club.luckylight.vo.Result;
import club.luckylight.vo.auth.InfoResponseVo;
import club.luckylight.vo.auth.LoginRequestVo;
import club.luckylight.vo.auth.LoginResponseVo;
import club.luckylight.vo.validation.ValidationResult;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 登录、注册、获取 token、注销
 *
 * @author liuruiming
 * @date 2018/5/9
 */
@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {this.authService = authService;}

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequestVo vo, HttpServletRequest request) {
        ValidationResult result = ValidationUtils.validateEntity(vo);
        if (result.isHasErrors()) {
            return new Result(50001, "用户名或密码不能为空", null);
        }

        User user = authService.login(vo);

        if (user == null) {
            return new Result(50002, "用户名或密码有误", null);
        }

        if (ObjectUtil.isNull(user.getRoleId())) {
            return new Result(50003, "对不起，您没有权限访问！", null);
        }

        if (user.getStatus() != 1) {
            return new Result(50004, "账户已被禁用", null);
        }

        LoginResponseVo loginResponseVo = null;
        try {
            loginResponseVo = new LoginResponseVo();
            loginResponseVo.setToken(JWTUtils.createToken(user.getUsername()));
        } catch (Exception e) {
            return new Result(50000, "服务异常", null);
        }

        // 发送流表，使得登录用户可以访问对应的资源
        try {
            authService.openPermission(user, NetworkUtil.getIpAddress(request));
        } catch (IOException e) {
            return new Result(50000, "服务异常", null);
        }

        return Result.ok(loginResponseVo);
    }

    @GetMapping("/info")
    public Result info(String token) {
        User user = null;
        try {
            user = authService.info(JWTUtils.verifyToken(token));
        } catch (UnsupportedEncodingException e) {
            return new Result(50014, "Token 过期了", null);
        }

        if (user == null) {
            return new Result(50005, "非法请求", null);
        }

        if (user.getStatus() != 1) {
            return new Result(50004, "账户已被禁用", null);
        }

        // 获取用户权限
        List<UseablePermissionDto> userPermission = authService.getUserPermission(user);

        // 封装返回结果
        InfoResponseVo infoResponseVo = new InfoResponseVo();
        infoResponseVo.setPermissions(userPermission);
        infoResponseVo.setName(user.getUsername());
        infoResponseVo.setAvatar(user.getAvatarUrl());

        return Result.ok(infoResponseVo);
    }

    @PostMapping("/logout")
    public Result logout(String token, HttpServletRequest request) {
        User user = null;
        try {
            user = authService.info(JWTUtils.verifyToken(token));
        } catch (UnsupportedEncodingException e) {
            return new Result(50014, "Token 过期了", null);
        }

        if (user == null) {
            return new Result(50005, "非法请求", null);
        }

        // 发送流表，使得用户不能访问对应的资源
        try {
            authService.closePermission(user, NetworkUtil.getIpAddress(request));
        } catch (IOException e) {
            return new Result(50000, "服务异常", null);
        }

        return Result.ok(null);
    }
}
