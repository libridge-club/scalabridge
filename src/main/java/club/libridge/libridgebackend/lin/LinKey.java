package club.libridge.libridgebackend.lin;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LinKey {

    AN("an", "annotation - bid explanation"), MB("mb", "make bid"), MC("mc", "make claim"), MD("md", "make deal? - cards in each players hand"),
    NT("nt", "note - commentary from kibitzers"), OB("ob", "other bid - bid made in the other room for the same board"), PC("pc", "play card"),
    PG("pg", "?? - separator node, used at the end of tricks and annotations"), PN("pn", "players names"),
    QX("qx", "?? - board info :o/c for open/closed. It also initiates the deal"), RS("rs", "results - results for previous sessions"),
    ST("st", "???"), SV("sv", "?? - vulnerability : o/n/e/b for None-NS-EW-Both"), VG("vg", "vugraph : Event information");

    private static Map<String, LinKey> symbolToKeyMap = new HashMap<>();

    static {
        for (LinKey current : LinKey.values()) {
            symbolToKeyMap.put(current.symbol.toLowerCase(), current);
        }
    }

    @Getter
    private final String symbol;
    @Getter
    private final String description;

    public static LinKey get(String symbol) {
        LinKey linKey = symbolToKeyMap.get(symbol.toLowerCase());
        if (linKey == null) {
            throw new IllegalArgumentException("There is no Lin Key with this symbol: " + symbol);
        } else {
            return linKey;
        }
    }

}
