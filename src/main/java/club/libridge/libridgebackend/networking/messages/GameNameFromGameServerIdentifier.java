
package club.libridge.libridgebackend.networking.messages;

import java.util.HashMap;
import java.util.Map;

import club.libridge.libridgebackend.networking.server.gameserver.GameServer;
import club.libridge.libridgebackend.networking.server.gameserver.MinibridgeGameServer;

public final class GameNameFromGameServerIdentifier {

  private static Map<Class<? extends GameServer>, String> gameServerClassesOfGameNames = new HashMap<>();

  private GameNameFromGameServerIdentifier() {
    throw new IllegalStateException("Utility class");
  }

  // Static initialization block to avoid doing this calculation every time
  // identify(..) is called.
  static {
    gameServerClassesOfGameNames.put(MinibridgeGameServer.class, "Minibridge");
  }

  public static String identify(Class<? extends GameServer> gameServerClass) {
    return gameServerClassesOfGameNames.get(gameServerClass);
  }

}
