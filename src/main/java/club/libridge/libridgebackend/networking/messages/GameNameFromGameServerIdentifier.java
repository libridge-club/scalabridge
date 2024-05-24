
package club.libridge.libridgebackend.networking.messages;

import java.util.HashMap;
import java.util.Map;

import club.libridge.libridgebackend.networking.server.gameserver.GameServer;
import club.libridge.libridgebackend.networking.server.gameserver.MinibridgeGameServer;

public final class GameNameFromGameServerIdentifier {

    private static final Map<Class<? extends GameServer>, String> GAME_NAMES_OF_GAME_SERVER_CLASSES;

    static {
        GAME_NAMES_OF_GAME_SERVER_CLASSES = new HashMap<>();
        GAME_NAMES_OF_GAME_SERVER_CLASSES.put(MinibridgeGameServer.class, "Minibridge");
    }

    private GameNameFromGameServerIdentifier() {
        throw new IllegalStateException("Utility class");
    }

    public static String identify(Class<? extends GameServer> gameServerClass) {
        return GAME_NAMES_OF_GAME_SERVER_CLASSES.get(gameServerClass);
    }

}
