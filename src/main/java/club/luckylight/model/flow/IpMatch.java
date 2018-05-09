package club.luckylight.model.flow;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class IpMatch implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "ip-protocol")
    private String ipProtocol;
}
