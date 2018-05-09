package club.luckylight.util;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;

public class ODLUtils {

    /**
     * 调用 OpenDaylight 的 RestAPI 下发流表
     *
     * @param ip       OpenDaylight 控制台 IP
     * @param port     OpenDaylight 控制台端口
     * @param switchId 交换机的 Id
     * @param flowId   流表项的 Id
     * @param body     流表项的内容
     * @return 调用结果
     */
    public static String addFlow(String ip, String port, String switchId, int flowId, String body) {
        return HttpRequest.put("http://"
            + ip
            + ":"
            + port
            + "/restconf/config/opendaylight-inventory:nodes/node/openflow:"
            + switchId
            + "/table/0"
            + "/flow/"
            + flowId)
                .header(Header.CONTENT_TYPE, "application/json")
                .header(Header.ACCEPT, "application/json")
                .basicAuth("admin", "admin")
                .body(body)
                .execute()
                .body();
    }

    /**
     * 调用 OpenDaylight 的 RestAPI 删除流表
     *
     * @param ip       OpenDaylight 控制台 IP
     * @param port     OpenDaylight 控制台端口
     * @param switchId 交换机的 Id
     * @param flowId   流表项的 Id
     * @return 调用结果
     */
    public static String deleteFlow(String ip, String port, String switchId, int flowId) {
        return HttpRequest.delete("http://"
            + ip
            + ":"
            + port
            + "/restconf/config/opendaylight-inventory:nodes/node/openflow:"
            + switchId
            + "/table/0"
            + "/flow/"
            + flowId)
                .header(Header.CONTENT_TYPE, "application/json")
                .header(Header.ACCEPT, "application/json")
                .basicAuth("admin", "admin")
                .execute()
                .body();
    }
}
