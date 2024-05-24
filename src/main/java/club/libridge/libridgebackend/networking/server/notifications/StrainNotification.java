package club.libridge.libridgebackend.networking.server.notifications;

import club.libridge.libridgebackend.core.Strain;
import lombok.Getter;

public class StrainNotification {

    @Getter
    private Strain strain;

    public void notifyAllWithStrain(Strain strain) {
        this.strain = strain;
        this.notifyAll(); // NOSONAR WONTFIX
    }

}
