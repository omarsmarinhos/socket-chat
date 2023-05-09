package Vista;

import java.io.Serializable;

public class ClienteInfo implements Serializable {
    private final String ip;
    private final String uuid;
    private final String nickname;
    private String status;

    public ClienteInfo(String ip, String uuid, String nickname, String status) {
        this.ip = ip;
        this.uuid = uuid;
        this.nickname = nickname;
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public String getUuid() {
        return uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClienteInfo{" +
                "ip='" + ip + '\'' +
                ", uuid='" + uuid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}