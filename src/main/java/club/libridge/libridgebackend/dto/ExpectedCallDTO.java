package club.libridge.libridgebackend.dto;

import java.util.UUID;

import club.libridge.libridgebackend.core.Call;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ExpectedCallDTO {

    @SuppressWarnings("unused")
    private UUID boardID;
    @Getter
    @Setter
    private Call call;

}
