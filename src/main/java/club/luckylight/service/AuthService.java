package club.luckylight.service;

import club.luckylight.dto.UseablePermissionDto;
import club.luckylight.model.User;
import club.luckylight.vo.auth.LoginRequestVo;

import java.util.List;

public interface AuthService {

    User login(LoginRequestVo vo);

    User info(String username);

    void openPermission(User user, String sourceIp);

    void closePermission(User user, String sourceIp);

    List<UseablePermissionDto> getUserPermission(User user);
}
