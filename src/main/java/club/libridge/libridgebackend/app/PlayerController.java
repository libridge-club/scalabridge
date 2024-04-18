package club.libridge.libridgebackend.app;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import club.libridge.libridgebackend.networking.websockets.PlayerListDTO;

@Controller
public class PlayerController {

    @Autowired
    private SimpMessagingTemplate template;

    public void getPlayers(PlayerListDTO playerList) {
        LOGGER.debug("Sending list of players to subscribers");
        this.template.convertAndSend("/topic/players", playerList);
    }

}
