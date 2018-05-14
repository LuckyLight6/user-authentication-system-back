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
@Table(name = "flow")
public class Flow implements Serializable {

    private static final Long serialVersionUID = 1L;

    /**
     * 权限Id，主键
     */
    @Id
    @Column(name = "id")
    private Integer id;

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
