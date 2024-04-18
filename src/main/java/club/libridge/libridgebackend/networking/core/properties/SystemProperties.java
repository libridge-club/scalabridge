package club.libridge.libridgebackend.networking.core.properties;

import static club.libridge.libridgebackend.networking.core.properties.PropertiesConstants.HOST;

public class SystemProperties {

    public String getHost() {
        return System.getProperty(HOST);
    }

}
