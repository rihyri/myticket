package jpastore.jpaticket.service;

import jpastore.jpaticket.domain.Reservation;
import jpastore.jpaticket.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyReserveServiceImpl implements MyReserveService {

    @Autowired
    private  final ReservationRepository reservationRepository;

    @Override
    public List<Reservation> findAllReservation(Principal principal) {
        String cid = principal.getName();
        return reservationRepository.findByUserName(cid);
    }

    @Override
    public ResponseEntity<Void> cancelReservation(Long reservation_id) {
        Optional<Reservation> optionalReserve = reservationRepository.findById(reservation_id);
        if (optionalReserve.isPresent()) {
            reservationRepository.deleteById(reservation_id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public Optional<Reservation> findReservation(Long reservation_id) {
        return reservationRepository.findById(reservation_id);
    }
}
