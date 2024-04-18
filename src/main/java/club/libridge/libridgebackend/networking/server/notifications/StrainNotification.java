package club.libridge.libridgebackend.networking.server.notifications;

import club.libridge.libridgebackend.core.Strain;

public class StrainNotification {

    private Strain strain;

    public void notifyAllWithStrain(Strain strain) {
        this.strain = strain;
        this.notifyAll(); // NOSONAR WONTFIX
    }

    public Strain getStrain() {
        return this.strain;
    }

}
