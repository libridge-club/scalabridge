package club.libridge.libridgebackend.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import club.libridge.libridgebackend.app.persistence.BoardFactory;
import club.libridge.libridgebackend.ben.BenCandidate;
import club.libridge.libridgebackend.ben.BenResponse;
import club.libridge.libridgebackend.ben.BenWebClient;
import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Call;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.Hand;
import club.libridge.libridgebackend.core.openingtrainer.OpeningSystem;
import club.libridge.libridgebackend.dto.CallWithProbabilityDTO;
import club.libridge.libridgebackend.dto.HandWithCandidateBidsDTO;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class OpeningTrainerService {

    @NonNull
    private final BoardFactory boardFactory;

    @NonNull
    private final OpeningSystem openingSystem;

    @NonNull
    private final BenWebClient benWebClient;

    private Hand getRandom() {
        return this.boardFactory.getRandom().getHandOf(Direction.NORTH);
    }

    private Call getCall(Hand hand) {
        Board boardWithProvidedHand = boardFactory.fromHandAndDirection(hand, Direction.NORTH);
        return this.openingSystem.getCall(boardWithProvidedHand);
    }

    public HandWithCandidateBidsDTO getRandomHandWithCandidateBidsDTO() {
        Hand hand = this.getRandom();
        Call call = this.getCall(hand);
        Optional<BenResponse> benResponseOptional = benWebClient.getBidForEmptyAuction(hand);
        if (benResponseOptional.isEmpty()) {
            return new HandWithCandidateBidsDTO(hand, call, null);
        } else {
            BenResponse benResponse = benResponseOptional.get();
            List<BenCandidate> candidates = benResponse.getCandidates();
            List<CallWithProbabilityDTO> list = candidates.stream().map(benCandidate -> {
                String pInsteadOfPass = "PASS".equals(benCandidate.getCall()) ? "P" : benCandidate.getCall();
                return new CallWithProbabilityDTO(pInsteadOfPass, benCandidate.getInstaScore());
            }).toList();
            return new HandWithCandidateBidsDTO(hand, call, list);
        }

    }
}
