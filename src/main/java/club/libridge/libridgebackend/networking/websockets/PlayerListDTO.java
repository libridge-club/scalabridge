package club.libridge.libridgebackend.networking.websockets;

import java.util.List;

public class PlayerListDTO {

    private List<PlayerDTO> list;

    public List<PlayerDTO> getList() {
        return list;
    }

    public void setList(List<PlayerDTO> list) {
        this.list = list;
    }

}
