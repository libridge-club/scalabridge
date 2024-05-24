package club.libridge.libridgebackend.networking.websockets;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerListDTO {

    private List<PlayerDTO> list;

}
