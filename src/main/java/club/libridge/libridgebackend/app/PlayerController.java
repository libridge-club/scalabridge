package club.libridge.libridgebackend.app;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import club.libridge.libridgebackend.networking.websockets.PlayerListDTO;

@RestController
public class PlayerController {

    private SimpMessagingTemplate template;

    @Autowired
    public PlayerController(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void getPlayers(PlayerListDTO playerList) {
        LOGGER.debug("Sending list of players to subscribers");
        this.template.convertAndSend("/topic/players", playerList);
    }

}
