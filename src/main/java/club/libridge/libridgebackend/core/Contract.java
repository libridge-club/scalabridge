package club.libridge.libridgebackend.core;

import lombok.NonNull;

public final class Contract {
    private final OddTricks oddTricks;
    private final Strain strain;
    private final boolean vulnerable;
    private final PenaltyStatus penaltyStatus;

    public Contract(@NonNull OddTricks oddTricks, @NonNull Strain strain, @NonNull PenaltyStatus penaltyStatus, boolean vulnerable) {
        this.oddTricks = oddTricks;
        this.strain = strain;
        this.penaltyStatus = penaltyStatus;
        this.vulnerable = vulnerable;
    }

    public int getLevel() {
        return this.oddTricks.getLevel();
    }

    public Strain getStrain() {
        return strain;
    }

    public boolean isDoubled() {
        return PenaltyStatus.DOUBLED == this.penaltyStatus;
    }

    public boolean isRedoubled() {
        return PenaltyStatus.REDOUBLED == this.penaltyStatus;
    }

    public boolean isVulnerable() {
        return vulnerable;
    }

    @Override
    public String toString() {
        String response = this.getLevel() + this.getStrain().getSymbol();
        if (this.isDoubled()) {
            response += "X";
        } else if (this.isRedoubled()) {
            response += "XX";
        }
        return response;
    }

}
