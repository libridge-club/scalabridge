package club.libridge.libridgebackend.networking.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest() // FIXME so that this does not start another server instance and use the already running server
@ActiveProfiles("development")
public class LibridgeServerIT {

    @Autowired
    private LibridgeServer server;

    @Test
    public void shouldHaveTwoMinibridgeTablesAtStartup() {
        int numberOfTablesAtStartup = 2;

        assertEquals(numberOfTablesAtStartup, server.getTablesDTO().size());
    }

}
