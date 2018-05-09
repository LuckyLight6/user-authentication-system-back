package club.luckylight.vo.auth;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class InfoResponseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roles;

    private String name;

    private String avatar;
}
