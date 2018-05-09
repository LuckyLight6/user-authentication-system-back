package club.luckylight.model.flow;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EthernetType implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "type")
    private String type;
}
