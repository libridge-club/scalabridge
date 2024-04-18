package club.libridge.libridgebackend.networking.client;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSession.Subscription;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import club.libridge.libridgebackend.clientapp.TableMessageFrameHandler;
import club.libridge.libridgebackend.core.Deal;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.dto.LobbyScreenTableDTO;
import club.libridge.libridgebackend.gui.listeners.ClientActionListener;
import club.libridge.libridgebackend.networking.rest.RestHTTPClient;

public class SBKingClient {

    private Direction direction;

    private Direction strainChooser;

    private Deal currentDeal;
    private boolean dealHasChanged = true;

    private Boolean rulesetValid = null;

    private ClientActionListener actionListener;

    private boolean spectator;

    private RestHTTPClient restHTTPClient;

    private String gameName = null;

    private List<LobbyScreenTableDTO> tables;

    private List<String> spectatorNames;

    private String nickname;

    private boolean shouldRedrawTables = true;

    private UUID identifier;

    private UUID currentTable = null;

    private StompSession stompSession;

    private Subscription dealSubscription;

    public void setStompSession(StompSession stompSession) {
        this.stompSession = stompSession;
    }

    public void setCurrentTable(UUID newTable) {
        if (this.currentTable != null && newTable == null) {
            LOGGER.info("Unsubscribing!");
            this.dealSubscription.unsubscribe(); // dealSubscription should not be null here
            this.dealSubscription = null;
        }
        if (newTable != null && !newTable.equals(this.currentTable)) {
            String topic = "/topic/table/" + newTable.toString();
            LOGGER.info("Subscribing to: {}", topic);
            this.dealSubscription = this.stompSession.subscribe(topic, new TableMessageFrameHandler(this));
            this.restHTTPClient.askForRefreshTable(newTable);
        }
        this.currentTable = newTable;
    }

    public void setActionListener(ClientActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public void setRestHTTPClient(RestHTTPClient restHTTPClient) {
        this.restHTTPClient = restHTTPClient;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isNicknameSet() {
        return this.nickname != null;
    }

    public void initializeDirection(Direction direction) {
        this.direction = direction;
    }

    public void finishDeal() {
        this.initializeEverythingToNextDeal();
    }

    public void initializeDeal() {
        this.initializeEverythingToNextDeal();
    }

    private void initializeEverythingToNextDeal() {
        this.unsetStrainChooser();
        this.unsetCurrentDeal();
        this.unsetRulesetValid();
    }

    public void resetBeforeEnteringTable() {
        this.unsetStrainChooser();
        this.unsetRulesetValid();
    }

    public Direction getDirection() {
        return this.direction;
    }

    public boolean isDirectionOrSpectatorSet() {
        return this.direction != null || this.spectator;
    }

    public void setStrainChooser(Direction direction) {
        this.strainChooser = direction;
    }

    private void unsetStrainChooser() {
        this.strainChooser = null;
    }

    public boolean isStrainChooserSet() {
        return this.strainChooser != null;
    }

    public Direction getStrainChooser() {
        return this.strainChooser;
    }

    public void setRulesetValid(boolean valid) {
        this.rulesetValid = valid;
    }

    private void unsetRulesetValid() {
        this.rulesetValid = null;
    }

    public void setCurrentDeal(Deal deal) {
        this.currentDeal = deal;
        this.dealHasChanged = true;
    }

    private void unsetCurrentDeal() {
        this.currentDeal = null;
    }

    public Deal getDeal() {
        this.dealHasChanged = false;
        return this.currentDeal;
    }

    public boolean getDealHasChanged() {
        return this.dealHasChanged;
    }

    public boolean isRulesetValidSet() {
        return this.rulesetValid != null;
    }

    public ClientActionListener getActionListener() {
        return this.actionListener;
    }

    public boolean isSpectator() {
        return this.spectator;
    }

    public void setSpectator(boolean spectator) {
        this.spectator = spectator;
    }

    public void sendChooseStrain(String strain) {
        this.restHTTPClient.chooseStrain(strain);
    }

    public void sendNickname() {
        this.restHTTPClient.sendNickname(this.nickname);
    }

    public void sendGetTables() {
        List<LobbyScreenTableDTO> tablesFromServer = this.restHTTPClient.getTables();
        if (tablesFromServer == null) {
            this.initializeTables();
        } else {
            this.setTables(tablesFromServer);
        }
    }

    public UUID sendCreateTable(String gameName) {
        String response = this.restHTTPClient.sendCreateTableMessage(gameName);
        ObjectMapper objectMapper = new ObjectMapper();
        UUID tableId = null;
        try {
            tableId = objectMapper.readValue(response, UUID.class);
        } catch (JsonProcessingException e) {
            LOGGER.error(e);
        }
        return tableId;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setTables(List<LobbyScreenTableDTO> tables) {
        if (tables.size() != this.tables.size()) {
            // This is a bad attempt at avoiding redraws when the tables don't change.
            // It should be done in the drawing, not here.
            this.tables = tables;
            this.shouldRedrawTables = true;
        }
    }

    public List<LobbyScreenTableDTO> getTables() {
        if (this.tables == null) {
            this.initializeTables();
        }
        this.shouldRedrawTables = false;
        return tables;
    }

    public void initializeTables() {
        this.tables = new ArrayList<LobbyScreenTableDTO>();
        this.shouldRedrawTables = true;
    }

    public boolean shouldRedrawTables() {
        return this.shouldRedrawTables;
    }

    public void setSpectatorNames(List<String> spectatorNames) {
        this.spectatorNames = spectatorNames;
    }

    public List<String> getSpectatorNames() {
        return this.spectatorNames;
    }

    public void sendGetSpectatorNames() {
        List<String> spectators = this.restHTTPClient.getSpectators();
        this.setSpectatorNames(spectators);
    }

    public void initializeId(String id) {
        this.restHTTPClient.setIdentifier(id);
        this.identifier = UUID.fromString(id);
    }

    public UUID getId() {
        return this.identifier;
    }

    public void sendJoinTable(UUID tableId) {
        this.restHTTPClient.sendJoinTableMessage(tableId);
    }

}
