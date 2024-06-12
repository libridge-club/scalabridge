package club.libridge.libridgebackend.app.persistence;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Transactional
    default void addOrIncrease(String address) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setAddress(address);
        Optional<ClientEntity> optionalClientEntity = this.findOne(Example.of(clientEntity));
        if (optionalClientEntity.isPresent()) {
            ClientEntity existingClientEntity = optionalClientEntity.get();
            existingClientEntity.increase();
            this.save(existingClientEntity);
        } else {
            ClientEntity clientEntityToSave = new ClientEntity(address);
            this.save(clientEntityToSave);
        }
    }

}
