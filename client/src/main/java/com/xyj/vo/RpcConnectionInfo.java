package com.xyj.vo;

import com.xyj.util.JsonUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
@Data
public class RpcConnectionInfo implements Serializable {

    private static final long serialVersionUID = -1102180003395190700L;

    // service host
    private String host;

    // service port
    private int port;

    // service info list
    private List<RpcServiceInfo> serviceInfoList;

    public String toJson() {
        String json = JsonUtil.objectToJson(this);
        return json;
    }

    public static RpcConnectionInfo fromJson(String json) {
        return JsonUtil.jsonToObject(json, RpcConnectionInfo.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RpcConnectionInfo that = (RpcConnectionInfo) o;
        return port == that.port &&
                Objects.equals(host, that.host) &&
                isListEquals(serviceInfoList, that.getServiceInfoList());
    }

    private boolean isListEquals(List<RpcServiceInfo> thisList, List<RpcServiceInfo> thatList) {
        if (thisList == null && thatList == null) {
            return true;
        }
        if ((thisList == null && thatList != null)
                || (thisList != null && thatList == null)
                || (thisList.size() != thatList.size())) {
            return false;
        }
        return thisList.containsAll(thatList) && thatList.containsAll(thisList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port, serviceInfoList.hashCode());
    }

    @Override
    public String toString() {
        return toJson();
    }


}
