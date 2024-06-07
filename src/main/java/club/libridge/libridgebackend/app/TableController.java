package club.libridge.libridgebackend.app;

import static club.libridge.libridgebackend.logging.LibridgeLogger.LOGGER;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.libridge.libridgebackend.dto.LobbyScreenTableDTO;
import club.libridge.libridgebackend.networking.server.LibridgeServer;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@RequestMapping("/tables")
@AllArgsConstructor
public class TableController {

    @NonNull
    private final LibridgeServer libridgeServer;

    @GetMapping
    public List<LobbyScreenTableDTO> getTables() {
        LOGGER.trace("getTables");
        return this.libridgeServer.getTablesDTO();
    }

}
