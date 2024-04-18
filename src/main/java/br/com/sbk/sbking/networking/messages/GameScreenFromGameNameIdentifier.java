package br.com.sbk.sbking.networking.messages;

import java.util.HashMap;
import java.util.Map;

import br.com.sbk.sbking.gui.screens.GameScreen;
import br.com.sbk.sbking.gui.screens.MinibridgeScreen;

public final class GameScreenFromGameNameIdentifier {

  private static Map<String, Class<? extends GameScreen>> gameScreenClassesOfGameNames = new HashMap<>();

  private GameScreenFromGameNameIdentifier() {
    throw new IllegalStateException("Utility class");
  }

  // Static initialization block to avoid doing this calculation every time
  // identify(..) is called.
  static {
    gameScreenClassesOfGameNames.put("Minibridge", MinibridgeScreen.class);
    gameScreenClassesOfGameNames.put("Mini-Minibridge", MinibridgeScreen.class);
    gameScreenClassesOfGameNames.put("TenCards-Minibridge", MinibridgeScreen.class);
  }

  public static Class<? extends GameScreen> identify(String gameName) {
    return gameScreenClassesOfGameNames.get(gameName);
  }

}
