package club.luckylight.model.flow;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Flow implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "id")
    private String flowId;

    @JSONField(name = "idle-timeout")
    private Long idleTimeout;

    @JSONField(name = "hard-timeout")
    private Long hardTimeout;

    @JSONField(name = "table_id")
    private String tableId;

    @JSONField(name = "priority")
    private String priority;

    @JSONField(name = "match")
    private Match match;

    @JSONField(name = "instructions")
    private Instructions instructions;
}
