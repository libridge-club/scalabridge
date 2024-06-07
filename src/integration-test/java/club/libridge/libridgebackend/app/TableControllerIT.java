package club.libridge.libridgebackend.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import club.libridge.libridgebackend.dto.LobbyScreenTableDTO;
import club.libridge.libridgebackend.networking.server.LibridgeServer;

@SpringBootTest() // FIXME so that this does not start another server instance and use the already running server
@ActiveProfiles("development")
public class TableControllerIT {

    @Autowired
    private TableController controller;

    @Autowired
    private LibridgeServer server;

    @Test
    public void getTables_ShouldReturnAllExistingTables() {
        List<LobbyScreenTableDTO> existingTables = server.getTablesDTO();

        List<LobbyScreenTableDTO> tables = controller.getTables();

        assertEquals(existingTables, tables);
    }

}
