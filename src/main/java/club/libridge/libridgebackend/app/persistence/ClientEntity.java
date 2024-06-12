package club.libridge.libridgebackend.app.persistence;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import club.libridge.libridgebackend.app.SecurityUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Client")
@Getter
@Setter
@Validated
public class ClientEntity {

    @Id
    @NotNull
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private byte[] address;

    @Column
    @NotNull
    private Long count;

    @Transactional
    public void increase() {
        if (count == null) {
            this.count = 0L;
        } else {
            this.count = this.count + 1L;
        }
    }

    public ClientEntity() {
    }

    public ClientEntity(String address) {
        this.setAddress(address);
        this.count = 0L;
    }

    // FIXME This is not anonymous.
    /**
     * The IP space is only 2^32, so a reverse lookup is completely feasible.
     * We should use a Bloom filter in order to get statistics on how many different clients
     * requested the server. That will make a reverse lookup harder, as the false positives
     * would be too high.
     *
     * For now, we use a hash and only provide an endpoint for the counts, not the addresses hashes.
     */
    public void setAddress(String address) {
        this.address = SecurityUtils.getSHA256Sum(address);
    }

}
