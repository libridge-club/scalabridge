package club.libridge.libridgebackend.networking.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Result {

    @Getter
    final int status;
    @Getter
    final String content;

}
