package club.luckylight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页跳转
 *
 * @author liuruiming
 * @date 2018/5/9
 */
@Controller
@CrossOrigin(origins = "*")
public class IndexController {

    @GetMapping("/")
    public String home() {
        return "/index.html";
    }
}
