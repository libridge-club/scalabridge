package club.libridge.libridgebackend.networking.core.properties;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NetworkingProperties {

    private static final String DEFAULT_IP = "164.90.254.243"; // NOSONAR WONTFIX

    private final FileProperties fileProperties;
    private final SystemProperties systemProperties;

    public String getHost() {
        String hostFromSystem = null;
        String hostFromFile = null;
        if (this.systemProperties != null) {
            hostFromSystem = this.systemProperties.getHost();
        }
        if (hostFromSystem != null && !hostFromSystem.isEmpty()) {
            return hostFromSystem;
        } else {
            if (this.fileProperties != null) {
                hostFromFile = this.fileProperties.getHost();
            }
            if (hostFromFile != null && !hostFromFile.isEmpty()) {
                return hostFromFile;
            } else {
                return DEFAULT_IP;
            }
        }
    }

}
