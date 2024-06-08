package club.libridge.libridgebackend.networking.websockets;

import java.util.UUID;

import club.libridge.libridgebackend.core.Direction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerDTO {

    private UUID player;
    private String nickname;
    private UUID table;
    private boolean spectator;
    private Direction direction;
    private String gameName;

}
