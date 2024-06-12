package club.libridge.libridgebackend.dto;

import club.libridge.libridgebackend.core.Call;
import club.libridge.libridgebackend.core.Hand;
import lombok.Value;

@Value
public class HandWithCallDTO {

    private String hand;
    private String call;

    public HandWithCallDTO(Hand hand, Call call) {
        this.hand = hand.toString();
        this.call = call.toString();
    }

}
