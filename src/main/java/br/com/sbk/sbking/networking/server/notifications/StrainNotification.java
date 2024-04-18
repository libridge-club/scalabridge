package br.com.sbk.sbking.networking.server.notifications;

import br.com.sbk.sbking.core.Strain;

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
