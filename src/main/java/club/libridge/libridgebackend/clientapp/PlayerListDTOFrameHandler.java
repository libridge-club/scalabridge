package club.libridge.libridgebackend.clientapp;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import java.lang.reflect.Type;
import java.util.Optional;
import java.util.UUID;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import club.libridge.libridgebackend.networking.client.SBKingClient;
import club.libridge.libridgebackend.networking.websockets.PlayerDTO;
import club.libridge.libridgebackend.networking.websockets.PlayerListDTO;

public class PlayerListDTOFrameHandler implements StompFrameHandler {

    private SBKingClient sbkingClient;

    public PlayerListDTOFrameHandler(SBKingClient sbkingClient) {
        this.sbkingClient = sbkingClient;
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return PlayerListDTO.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        LOGGER.debug("Received message: --PlayerListDTO--");
        PlayerListDTO playerListDTO = (PlayerListDTO) payload;

        UUID id = this.sbkingClient.getId();
        Optional<PlayerDTO> playerFromList = playerListDTO.getList().stream().filter(p -> id.equals(p.getPlayer()))
                .findAny();
        if (playerFromList.isPresent()) {
            PlayerDTO myself = playerFromList.get();
            LOGGER.debug("My information:"
                    + "\nUUID:" + myself.getPlayer().toString()
                    + "\nTable:" + myself.getTable()
                    + "\nDirection:" + myself.getDirection()
                    + "\nIsSpectator:" + myself.getSpectator()
                    + "\nGameName:" + myself.getGameName());

            this.sbkingClient.setCurrentTable(myself.getTable());
            this.sbkingClient.setSpectator(myself.getSpectator());
            this.sbkingClient.initializeDirection(myself.getDirection());
            this.sbkingClient.setGameName(myself.getGameName());
        }
    }

}
