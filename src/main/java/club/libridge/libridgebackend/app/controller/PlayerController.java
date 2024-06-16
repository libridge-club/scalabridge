package club.libridge.libridgebackend.app.controller;

import static club.libridge.libridgebackend.logging.LibridgeLogger.LOGGER;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import club.libridge.libridgebackend.networking.websockets.PlayerListDTO;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
public class PlayerController {

    @NonNull
    private final SimpMessagingTemplate template;

    public void getPlayers(PlayerListDTO playerList) {
        LOGGER.debug("Sending list of players to subscribers");
        this.template.convertAndSend("/topic/players", playerList);
    }

}
