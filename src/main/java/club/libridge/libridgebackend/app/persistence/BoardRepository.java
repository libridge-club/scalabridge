package club.libridge.libridgebackend.app.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, UUID> {

    @Query("SELECT b FROM Board b ORDER BY RAND() LIMIT 1")
    Optional<BoardEntity> getRandom();

}
