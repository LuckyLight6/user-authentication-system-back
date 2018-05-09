package club.luckylight.controller;

import club.luckylight.model.User;
import club.luckylight.service.AuthService;
import club.luckylight.util.JWTUtils;
import club.luckylight.util.ValidationUtils;
import club.luckylight.vo.Result;
import club.luckylight.vo.auth.InfoResponseVo;
import club.luckylight.vo.auth.LoginRequestVo;
import club.luckylight.vo.auth.LoginResponseVo;
import club.luckylight.vo.validation.ValidationResult;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * 登录、注册、获取 token、注销
 *
 * @author liuruiming
 * @date 2018/5/9
 */
@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequestVo vo) {
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

        return Result.ok(loginResponseVo);
    }

//    @GetMapping("/info")
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

        // 封装返回结果
        InfoResponseVo infoResponseVo = new InfoResponseVo();
        infoResponseVo.setRoles(user.getRoleName());
        infoResponseVo.setName(user.getUsername());
        infoResponseVo.setAvatar(user.getAvatarUrl());

        return Result.ok(infoResponseVo);
    }

    @PostMapping("/logout")
    public Result logout() {
        return Result.ok(null);
    }
}
