package club.libridge.libridgebackend.app;

import org.springframework.stereotype.Service;

import club.libridge.libridgebackend.app.persistence.BoardFactory;
import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Call;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.Hand;
import club.libridge.libridgebackend.core.openingtrainer.OpeningSystem;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class OpeningTrainerService {

    @NonNull
    private final BoardFactory boardFactory;

    @NonNull
    private final OpeningSystem openingSystem;

    public Hand getRandom() {
        return this.boardFactory.getRandom().getHandOf(Direction.NORTH);
    }

    public Call getCall(Hand hand) {
        Board boardWithProvidedHand = boardFactory.fromHandAndDirection(hand, Direction.NORTH);
        return this.openingSystem.getCall(boardWithProvidedHand);
    }
}
