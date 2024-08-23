package jpastore.jpaticket.repository;

import jpastore.jpaticket.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "select reservation_num from reservation where title = :game", nativeQuery = true)
    List<String> findReservationSeats(@Param("game") String game);
    List<Reservation> findByUserName(String userName);

}
