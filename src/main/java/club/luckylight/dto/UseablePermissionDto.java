package club.luckylight.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UseablePermissionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限 Id
     */
    private Integer permissionId;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限 Ip
     */
    private String permissionIp;

    /**
     * 权限端口
     */
    private Integer permissionPort;

    /**
     * 权限描述
     */
    private String remark;
}
