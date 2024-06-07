package club.libridge.libridgebackend.app;

import static club.libridge.libridgebackend.logging.LibridgeLogger.LOGGER;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import club.libridge.libridgebackend.networking.websockets.TableMessageDTO;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
public class TableWebsocketController {

    @NonNull
    private final SimpMessagingTemplate template;

    private String getDestination(TableMessageDTO tableDealDTO) {
        return "/topic/table/" + tableDealDTO.getTableId();
    }

    public void sendMessage(TableMessageDTO tableDealDTO) {
        String destination = this.getDestination(tableDealDTO);
        LOGGER.debug("Sending {} to: {}", tableDealDTO.getMessage(), destination);
        this.template.convertAndSend(destination, tableDealDTO);
    }

}
