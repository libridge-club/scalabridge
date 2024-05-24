package club.libridge.libridgebackend.networking.server.notifications;

import club.libridge.libridgebackend.core.Card;
import club.libridge.libridgebackend.core.Direction;
import lombok.Getter;

public class CardPlayNotification {

    @Getter
    private Card card;
    @Getter
    private Direction direction;

    public void notifyAllWithCardAndDirection(Card card, Direction direction) {
        this.card = card;
        this.direction = direction;
        this.notifyAll(); // NOSONAR WONTFIX
    }

}
