package club.luckylight.model.flow;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Instruction implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "order")
    private Long order = 0L;

    @JSONField(name = "apply-actions")
    private ApplyActions applyActions;
}
