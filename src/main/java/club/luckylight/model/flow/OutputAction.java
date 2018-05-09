package club.luckylight.model.flow;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OutputAction implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "output-node-connector")
    private String outputNodeConnector = "NORMAL";

    @JSONField(name = "max-length")
    private Long maxLength = 0L;
}
