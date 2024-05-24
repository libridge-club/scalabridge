package club.libridge.libridgebackend.networking.messages;

import java.util.HashMap;
import java.util.Map;

import club.libridge.libridgebackend.networking.server.gameserver.GameServer;
import club.libridge.libridgebackend.networking.server.gameserver.MinibridgeGameServer;

public final class GameServerFromGameNameIdentifier {

    private static final Map<String, Class<? extends GameServer>> GAME_SERVER_CLASSES_OF_GAME_NAMES;

    static {
        GAME_SERVER_CLASSES_OF_GAME_NAMES = new HashMap<>();
        GAME_SERVER_CLASSES_OF_GAME_NAMES.put("Minibridge", MinibridgeGameServer.class);
    }

    private GameServerFromGameNameIdentifier() {
        throw new IllegalStateException("Utility class");
    }

    public static Class<? extends GameServer> identify(String gameName) {
        return GAME_SERVER_CLASSES_OF_GAME_NAMES.get(gameName);
    }

}
