package club.luckylight.model.flow;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EthernetMatch implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "ethernet-source")
    private EthernetSource ethernetSource;

    @JSONField(name = "ethernet-destination")
    private EthernetDestination ethernetDestination;

    @JSONField(name = "ethernet-type")
    private EthernetType ethernetType;
}
