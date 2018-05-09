package club.luckylight.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Table(name = "permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限Id，主键
     */
    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

    /**
     * 权限名称
     */
    @Column(name = "permission_name")
    private String permissionName;

    /**
     * 权限 ip
     */
    @Column(name = "permission_ip")
    private String permissionIp;

    /**
     * 权限端口
     */
    @Column(name = "permission_port")
    private Integer permissionPort;

    /**
     * 对权限的描述
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 流表项的 Id
     */
    @Column(name = "flow_id")
    private Integer flowId;

    /**
     * 流表项的内容
     */
    @Column(name = "flow_body")
    private String flowBody;

    /**
     * 状态
     */
    @Column(name = "status")
    private Byte status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}