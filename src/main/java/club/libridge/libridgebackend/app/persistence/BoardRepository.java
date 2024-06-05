package club.libridge.libridgebackend.app.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import club.libridge.libridgebackend.core.RandomUtils;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, UUID> {

    /**
     * This works by first getting the row count, then generating a
     * random index between 0 and the row count and finally
     * using this random index as an offset.
     */
    default Optional<BoardEntity> getRandom(RandomUtils randomUtils) {
        long count = this.count();
        if (count <= 0) {
            return Optional.empty();
        }
        Long randomIndex = randomUtils.nextLong(count);
        if (randomIndex > Integer.MAX_VALUE) { // PageRequest.of cannot use the long value :(
            randomIndex %= Integer.MAX_VALUE; // This is probably made inside Long::intValue() but the documentation is not clear
        }
        try {
            return Optional.of(this.findAll(PageRequest.of(randomIndex.intValue(), 1)).getContent().getFirst());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
