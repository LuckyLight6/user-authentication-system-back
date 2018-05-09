package club.luckylight.vo.auth;

import club.luckylight.dto.UseablePermissionDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class InfoResponseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<UseablePermissionDto> permissions;

    private String name;

    private String avatar;
}
