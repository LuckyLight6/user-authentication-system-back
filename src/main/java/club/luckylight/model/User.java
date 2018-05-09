package club.luckylight.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id，主键
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码，存储加密后的
     */
    @Column(name = "password")
    private String password;

    /**
     * 头像的地址
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 用户对应角色的Id
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 用户对应角色的名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新日期
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 账号的状态，默认为可用。1-可用，0-不可用
     */
    @Column(name = "status")
    private Byte status;

    /**
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;
}