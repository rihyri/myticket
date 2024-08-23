package jpastore.jpaticket.service;

import jpastore.jpaticket.domain.Reservation;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface MyReserveService {

    List<Reservation> findAllReservation(Principal principal);

    // 예매 취소
    ResponseEntity<Void> cancelReservation(Long reservation_id);

    Optional<Reservation> findReservation(Long reservation_id);
}
