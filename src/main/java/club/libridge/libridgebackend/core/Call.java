package club.libridge.libridgebackend.core;

public abstract class Call {

    public boolean isPass() {
        return (this instanceof PassingCall);
    }

    public boolean isDouble() {
        if (this instanceof PunitiveCall punitiveCall) {
            return punitiveCall.isDouble();
        }
        return false;
    }

    public boolean isRedouble() {
        if (this instanceof PunitiveCall punitiveCall) {
            return punitiveCall.isRedouble();
        }
        return false;
    }

    public boolean isBid() {
        return (this instanceof Bid);
    }

}
