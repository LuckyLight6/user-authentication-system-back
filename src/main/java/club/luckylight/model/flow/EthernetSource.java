package club.luckylight.model.flow;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EthernetSource implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "address")
    private String address;

    @JSONField(name = "mask")
    private String mask;
}
