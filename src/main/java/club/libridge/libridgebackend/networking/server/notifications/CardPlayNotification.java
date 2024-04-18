package club.libridge.libridgebackend.networking.server.notifications;

import club.libridge.libridgebackend.core.Card;
import club.libridge.libridgebackend.core.Direction;

public class CardPlayNotification {

    private Card card;
    private Direction direction;

    public void notifyAllWithCardAndDirection(Card card, Direction direction) {
        this.card = card;
        this.direction = direction;
        this.notifyAll(); // NOSONAR WONTFIX
    }

    public Card getCard() {
        return this.card;
    }

    public Direction getDirection() {
        return this.direction;
    }

}
