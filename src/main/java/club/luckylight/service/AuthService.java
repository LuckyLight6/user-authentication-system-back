package club.luckylight.service;

import club.luckylight.model.User;
import club.luckylight.vo.auth.LoginRequestVo;

public interface AuthService {

    User login(LoginRequestVo vo);

    User info(String username);
}
