package br.com.sbk.sbking.core;

public class Score {

    private int northSouthTricks = 0;
    private int eastWestTricks = 0;

    public int getNorthSouthTricks() {
        return northSouthTricks;
    }

    public int getEastWestTricks() {
        return eastWestTricks;
    }

    public void addTrickToDirection(Trick trick, Direction winner) {
        if (winner.isNorthSouth()) {
            this.northSouthTricks++;
        } else {
            this.eastWestTricks++;
        }
    }

    public void subtractTrickFromDirection(Trick trick, Direction winner) {
        if (winner.isNorthSouth()) {
            this.northSouthTricks--;
        } else {
            this.eastWestTricks--;
        }
    }

    public int getAlreadyPlayedTricks() {
        return this.eastWestTricks + this.northSouthTricks;
    }

    public void finishScore(Direction winner, int totalPoints) {
        int remainingPoints = totalPoints - this.getAlreadyPlayedTricks();
        if (winner.isNorthSouth()) {
            this.northSouthTricks += remainingPoints;
        } else {
            this.eastWestTricks += remainingPoints;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + eastWestTricks;
        result = prime * result + northSouthTricks;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Score other = (Score) obj;
        if (eastWestTricks != other.eastWestTricks) {
            return false;
        }
        if (northSouthTricks != other.northSouthTricks) {
            return false;
        }
        return true;
    }
}
