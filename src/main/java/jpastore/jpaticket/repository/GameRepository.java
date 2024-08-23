package jpastore.jpaticket.repository;

import jpastore.jpaticket.domain.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g From Game g ORDER BY g.gameDate ASC")
    List<Game> findTop4ByOrderByCreatedAsc();
}
