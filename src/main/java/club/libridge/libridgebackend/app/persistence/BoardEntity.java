package club.libridge.libridgebackend.app.persistence;

import java.util.UUID;

import org.springframework.validation.annotation.Validated;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.PavlicekNumber;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Access(AccessType.FIELD)
@Entity(name = "Board")
@Getter
@Setter
@Validated
public class BoardEntity {

    @Id
    @NotNull
    @GeneratedValue
    private UUID id;

    @Access(AccessType.PROPERTY)
    @Column
    @NotNull
    private String pavlicekNumber = "";

    /**
     * @deprecated Spring eyes only
     */
    @Deprecated
    private BoardEntity() { }

    public BoardEntity(@NotNull Board board) {
        PavlicekNumber pavlicekNumberGenerator = new PavlicekNumber();
        this.pavlicekNumber = pavlicekNumberGenerator.getNumberFromBoard(board).toString();
    }

    public BoardEntity(@NotNull String pavlicekNumber) {
        this.pavlicekNumber = pavlicekNumber;
    }

    /**
     * This is the inverse side of the relationship
     */
    @OneToOne(mappedBy = "boardEntity", cascade = CascadeType.ALL)
    private DoubleDummyTableEntity doubleDummyTableEntity;

}
