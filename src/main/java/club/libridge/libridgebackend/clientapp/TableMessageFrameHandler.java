package club.libridge.libridgebackend.clientapp;

import static club.libridge.libridgebackend.core.MessageTypes.DEAL_MESSAGE;
import static club.libridge.libridgebackend.core.MessageTypes.FINISH_DEAL_MESSAGE;
import static club.libridge.libridgebackend.core.MessageTypes.INITIALIZE_DEAL_MESSAGE;
import static club.libridge.libridgebackend.core.MessageTypes.INVALID_RULESET_MESSAGE;
import static club.libridge.libridgebackend.core.MessageTypes.STRAIN_CHOOSER_MESSAGE;
import static club.libridge.libridgebackend.core.MessageTypes.VALID_RULESET_MESSAGE;
import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import java.lang.reflect.Type;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import club.libridge.libridgebackend.networking.client.SBKingClient;
import club.libridge.libridgebackend.networking.websockets.TableMessageDTO;

public class TableMessageFrameHandler implements StompFrameHandler {

    private SBKingClient sbkingClient;

    public TableMessageFrameHandler(SBKingClient sbkingClient) {
        this.sbkingClient = sbkingClient;
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return TableMessageDTO.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        TableMessageDTO tableDealDTO = (TableMessageDTO) payload;
        String messageType = tableDealDTO.getMessage();
        if (DEAL_MESSAGE.equals(messageType)) {
            LOGGER.trace("Received message: --TableMessage:{}--", DEAL_MESSAGE);
            this.sbkingClient.setCurrentDeal(tableDealDTO.getDeal());
        } else if (FINISH_DEAL_MESSAGE.equals(messageType)) {
            LOGGER.trace("Received message: --TableMessage:{}--", FINISH_DEAL_MESSAGE);
            this.sbkingClient.finishDeal();
        } else if (INITIALIZE_DEAL_MESSAGE.equals(messageType)) {
            LOGGER.trace("Received message: --TableMessage:{}--", INITIALIZE_DEAL_MESSAGE);
            this.sbkingClient.initializeDeal();
        } else if (INVALID_RULESET_MESSAGE.equals(messageType)) {
            LOGGER.trace("Received message: --TableMessage:{}--", INVALID_RULESET_MESSAGE);
            this.sbkingClient.setRulesetValid(false);
        } else if (VALID_RULESET_MESSAGE.equals(messageType)) {
            LOGGER.trace("Received message: --TableMessage:{}--", VALID_RULESET_MESSAGE);
            this.sbkingClient.setRulesetValid(true);
        } else if (STRAIN_CHOOSER_MESSAGE.equals(messageType)) {
            LOGGER.trace("Received message: --TableMessage:{}--", STRAIN_CHOOSER_MESSAGE);
            this.sbkingClient.setStrainChooser(tableDealDTO.getDirection());
        } else {
            LOGGER.error("Could not understand message.");
            LOGGER.error(headers);
            LOGGER.error("Message: {}", tableDealDTO.getMessage());
            LOGGER.error("Table: {}", tableDealDTO.getTableId());
            LOGGER.error("Deal: {}", tableDealDTO.getDeal());
            LOGGER.error("Content: {}", tableDealDTO.getContent());
            LOGGER.error("Direction: {}", tableDealDTO.getDirection());
        }
    }

}
