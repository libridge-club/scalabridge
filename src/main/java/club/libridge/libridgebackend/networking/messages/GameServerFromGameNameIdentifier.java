package club.libridge.libridgebackend.networking.messages;

import java.util.HashMap;
import java.util.Map;

import club.libridge.libridgebackend.networking.server.gameserver.GameServer;
import club.libridge.libridgebackend.networking.server.gameserver.MinibridgeGameServer;

public final class GameServerFromGameNameIdentifier {

  private static Map<String, Class<? extends GameServer>> gameServerClassesOfGameNames = new HashMap<>();

  private GameServerFromGameNameIdentifier() {
    throw new IllegalStateException("Utility class");
  }

  // Static initialization block to avoid doing this calculation every time
  // identify(..) is called.
  static {
    gameServerClassesOfGameNames.put("Minibridge", MinibridgeGameServer.class);
  }

  public static Class<? extends GameServer> identify(String gameName) {
    return gameServerClassesOfGameNames.get(gameName);
  }

}
