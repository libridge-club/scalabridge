package club.libridge.libridgebackend.app;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import club.libridge.libridgebackend.app.persistence.BoardRepository;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.dto.BoardDTO;
import club.libridge.libridgebackend.dto.LobbyScreenTableDTO;
import club.libridge.libridgebackend.networking.messages.GameServerFromGameNameIdentifier;
import club.libridge.libridgebackend.networking.server.SBKingServer;
import club.libridge.libridgebackend.networking.server.gameserver.GameServer;

@RestController
class AppController {

    private static final String ACCEPT_CANNOT_BE_EMPTY = "Accept cannot be empty. It must be true or false.";
    @Autowired
    private ServerComponent serverComponent;
    @Autowired
    BoardRepository boardRepository;

    private SBKingServer getServer() {
        return this.serverComponent.getSbKingServer();
    }

    private UUID getUUID(String uuidString) {
        return UUID.fromString(uuidString);
    }

    @GetMapping("/connect")
    public ResponseEntity<UUID> connect() {
        LOGGER.info("connect");
        UUID playerId = UUID.randomUUID();
        // TODO remove this player at disconnection in the future
        this.getServer().addUnnammedPlayer(playerId);
        LOGGER.info("ID: {}", playerId);
        return new ResponseEntity<>(playerId, HttpStatus.CREATED);
    }

    @PostMapping("/playcard")
    public void playCard(@RequestHeader("PlayerUUID") String playerUUID, @RequestBody RequestCard requestCard) {
        LOGGER.trace("playCard");
        this.getServer().play(requestCard.getCard(), getUUID(playerUUID));
    }

    @PostMapping("/table")
    public ResponseEntity<UUID> createTable(@RequestHeader("PlayerUUID") String playerUUID,
            @RequestBody RequestWithString requestWithString) {
        LOGGER.trace("createTable");
        Class<? extends GameServer> gameServerClass = GameServerFromGameNameIdentifier
                .identify(requestWithString.getContent());
        LOGGER.info("Player {} created a {} table.", playerUUID, gameServerClass.getSimpleName());
        UUID tableId = this.getServer().createTable(gameServerClass);
        return new ResponseEntity<>(tableId, HttpStatus.CREATED);
    }

    @PostMapping("/table/join/{tableId}")
    public void joinTable(@RequestHeader("PlayerUUID") String playerUUID, @PathVariable String tableId) {
        LOGGER.trace("joinTable");
        this.getServer().joinTable(getUUID(playerUUID), UUID.fromString(tableId));
    }

    @PostMapping("/table/refresh/{tableId}")
    public void refreshTable(@RequestHeader("PlayerUUID") String playerUUID, @PathVariable String tableId) {
        LOGGER.trace("refreshTable");
        this.getServer().refreshTable(UUID.fromString(tableId));
    }

    @PostMapping("/table/leave") // Each player can only be in one table for now
    public void leaveTable(@RequestHeader("PlayerUUID") String playerUUID) {
        LOGGER.trace("leaveTable");
        this.getServer().leaveTable(getUUID(playerUUID));
    }

    @PostMapping("/moveToSeat/{directionAbbreviation}")
    public void moveToSeat(@RequestHeader("PlayerUUID") String playerUUID, @PathVariable String directionAbbreviation) {
        LOGGER.trace("moveToSeat");
        Direction direction = Direction.getFromAbbreviation(directionAbbreviation.charAt(0));
        this.getServer().moveToSeat(direction, getUUID(playerUUID));
    }

    @PutMapping("/player/nickname")
    public void setNickname(@RequestHeader("PlayerUUID") String playerUUID,
            @RequestBody RequestWithString requestWithString) {
        LOGGER.trace("setNickname");
        this.getServer().setNickname(
                getUUID(playerUUID), requestWithString.getContent());
    }

    @PostMapping("/claim")
    public void claim(@RequestHeader("PlayerUUID") String playerUUID) {
        LOGGER.trace("claim");
        this.getServer().claim(getUUID(playerUUID));
    }

    @PostMapping("/claim/{accept}")
    public void handleClaim(@RequestHeader("PlayerUUID") String playerUUID, @PathVariable Boolean accept) {
        LOGGER.trace("handleClaim");
        LOGGER.trace(accept);
        if (accept == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ACCEPT_CANNOT_BE_EMPTY);
        }
        this.getServer().claim(getUUID(playerUUID));
        if (accept) {
            this.getServer().acceptClaim(getUUID(playerUUID));
        } else {
            this.getServer().rejectClaim(getUUID(playerUUID));
        }
    }

    @PostMapping("/undo")
    public void undo(@RequestHeader("PlayerUUID") String playerUUID) {
        LOGGER.trace("undo");
        this.getServer().undo(getUUID(playerUUID));
    }

    @PostMapping("/chooseStrain")
    public void chooseStrain(@RequestHeader("PlayerUUID") String playerUUID,
            @RequestBody RequestWithString requestWithString) {
        LOGGER.trace("chooseStrain");
        this.getServer().chooseStrain(requestWithString.getContent(), getUUID(playerUUID));
    }

    @GetMapping("/spectators")
    public List<String> getSpectators(@RequestHeader("PlayerUUID") String playerUUID) {
        LOGGER.trace("getSpectators");
        return this.getServer().getSpectatorList(getUUID(playerUUID));
    }

    @GetMapping("/tables")
    public List<LobbyScreenTableDTO> getTables() {
        LOGGER.trace("getTables");
        return this.getServer().getTablesDTO();
    }

    @GetMapping("/randomBoard")
    public BoardDTO getRandomBoard() {
        LOGGER.trace("getRandomBoard");
        return this.getServer().getRandomBoard(boardRepository);
    }

    @GetMapping("/437c542d77291aaff96d6b223b798628")
    public void magicNumberCreateTablesFromFile() {
        LOGGER.trace("magicNumberCreateTablesFromFile");
        this.getServer().magicNumberCreateTablesFromFile(boardRepository);
    }


}
