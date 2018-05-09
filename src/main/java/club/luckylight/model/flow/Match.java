package club.luckylight.model.flow;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Match implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "ethernet-match")
    private EthernetMatch ethernetMatch;

    @JSONField(name = "ipv4-source")
    private String ipv4Source;

    @JSONField(name = "ipv4-destination")
    private String ipv4Destination;

    @JSONField(name = "tcp-destination-port")
    private String tcpDestinationPort;

    @JSONField(name = "tcp-source-port")
    private String tcpSourcePort;

    @JSONField(name = "ip-match")
    private IpMatch ipMatch;
}
