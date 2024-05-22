package club.libridge.libridgebackend;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class TestWithMocks {
    private AutoCloseable openMocks;

    @BeforeEach
    public void initializeMocks() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void closeMocks() throws Exception {
        openMocks.close();
    }
}
