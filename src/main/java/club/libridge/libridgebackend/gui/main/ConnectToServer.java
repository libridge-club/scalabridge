package club.libridge.libridgebackend.gui.main;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import club.libridge.libridgebackend.core.constants.ErrorCodes;
import club.libridge.libridgebackend.gui.listeners.ClientActionListener;
import club.libridge.libridgebackend.networking.client.SBKingClient;
import club.libridge.libridgebackend.networking.core.properties.FileProperties;
import club.libridge.libridgebackend.networking.core.properties.NetworkingProperties;
import club.libridge.libridgebackend.networking.core.properties.SystemProperties;
import club.libridge.libridgebackend.networking.rest.RestHTTPClient;

public final class ConnectToServer {

    private ConnectToServer() {
        throw new IllegalStateException("Utility class");
    }

    public static String getIPAddress() {
        NetworkingProperties networkingProperties = new NetworkingProperties(new FileProperties(),
                new SystemProperties());
        String hostname = networkingProperties.getHost();
        if (isValidIP(hostname)) {
            return hostname;
        } else {
            throw new InvalidIpException();
        }
    }

    public static SBKingClient connectToServer() {
        RestHTTPClient restHTTPClient = new RestHTTPClient(getIPAddress());
        return createWithRestHttpClient(restHTTPClient);
    }

    private static boolean isValidIP(String ipAddr) {
        Pattern ptn = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
        Matcher mtch = ptn.matcher(ipAddr);
        return mtch.find();
    }

    private static SBKingClient createWithRestHttpClient(RestHTTPClient restHTTPClient) {
        SBKingClient sbKingClient = new SBKingClient();
        sbKingClient.setRestHTTPClient(restHTTPClient);
        sbKingClient
                .setActionListener(
                        new ClientActionListener(restHTTPClient));
        LOGGER.info("Trying to connect.");
        try {
            UUID playerUUID = restHTTPClient.connect();
            String playerIdentifier = playerUUID.toString();
            sbKingClient.initializeId(playerIdentifier);
            LOGGER.info("Connected with identifier: {}.", playerIdentifier);
        } catch (Exception e) {
            LOGGER.fatal("Could not connect to server");
            LOGGER.fatal(e);
            System.exit(ErrorCodes.COULD_NOT_CONNECT_TO_SERVER);
        }
        return sbKingClient;
    }

}
