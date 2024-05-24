package club.libridge.libridgebackend.app.persistence;

import java.math.BigInteger;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.PavlicekNumber;
import club.libridge.libridgebackend.core.boarddealer.BoardDealer;
import club.libridge.libridgebackend.core.boarddealer.CardDeck;
import club.libridge.libridgebackend.core.boarddealer.Complete52CardDeck;
import club.libridge.libridgebackend.core.boarddealer.ShuffledBoardDealer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@Validated
@Component
@AllArgsConstructor
public class BoardFactory {

    @NotNull
    @NonNull
    private final PavlicekNumber pavlicekNumberGenerator;

    public Board fromEntity(BoardEntity boardEntity) {
        return pavlicekNumberGenerator.getBoardFromNumber(new BigInteger(boardEntity.getPavlicekNumber()));
    }

    public Board getRandom() {
        BoardDealer boardDealer = new ShuffledBoardDealer();
        CardDeck anyCardDeck = new Complete52CardDeck();
        return boardDealer.dealBoard(Direction.NORTH, anyCardDeck.getDeck());
    }

}
