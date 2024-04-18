package club.libridge.libridgebackend.gui.listeners;

import java.util.UUID;

import club.libridge.libridgebackend.core.Card;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.gui.jelements.AcceptClaimButton;
import club.libridge.libridgebackend.gui.jelements.CardButton;
import club.libridge.libridgebackend.gui.jelements.ClaimButton;
import club.libridge.libridgebackend.gui.jelements.JoinTableButton;
import club.libridge.libridgebackend.gui.jelements.LeaveTableButton;
import club.libridge.libridgebackend.gui.jelements.RejectClaimButton;
import club.libridge.libridgebackend.gui.jelements.SitOrLeaveButton;
import club.libridge.libridgebackend.gui.jelements.UndoButton;
import club.libridge.libridgebackend.networking.rest.RestHTTPClient;

public class ClientActionListener implements java.awt.event.ActionListener {

    private RestHTTPClient restClient;

    public ClientActionListener(RestHTTPClient restClient) {
        super();
        this.restClient = restClient;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent event) {
        Object source = event.getSource();

        if (source instanceof CardButton) {
            CardButton clickedCardButton = (CardButton) source;
            Card card = clickedCardButton.getCard();
            try {
                restClient.play(card);
            } catch (RuntimeException e) {
                throw e;
            }
        } else if (source instanceof SitOrLeaveButton) {
            SitOrLeaveButton clickedSitOrLeaveButton = (SitOrLeaveButton) source;
            Direction direction = (Direction) clickedSitOrLeaveButton.getClientProperty("direction");
            restClient.moveToSeat(direction);
        } else if (source instanceof UndoButton) {
            restClient.undo();
        } else if (source instanceof ClaimButton) {
            restClient.claim();
        } else if (source instanceof AcceptClaimButton) {
            restClient.handleClaim(true);
        } else if (source instanceof RejectClaimButton) {
            restClient.handleClaim(false);
        } else if (source instanceof JoinTableButton) {
            JoinTableButton button = (JoinTableButton) source;
            UUID tableId = (UUID) button.getClientProperty("tableId");
            restClient.sendJoinTableMessage(tableId);
        } else if (source instanceof LeaveTableButton) {
            restClient.leaveTable();
        }
    }

}
