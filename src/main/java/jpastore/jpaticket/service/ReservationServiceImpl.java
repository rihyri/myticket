package jpastore.jpaticket.service;

import jpastore.jpaticket.domain.Reservation;
import jpastore.jpaticket.dto.ReservationDto;
import jpastore.jpaticket.repository.MemberRepository;
import jpastore.jpaticket.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void processReservation (ReservationDto reservationDto) {
        log.info("예약 정보 : {}", reservationDto);
    }

    @Override
    public boolean cancelReservation(Long reservation_id) {
        try {
            reservationRepository.deleteById(reservation_id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ReservationDto> findAllReservations() {
        return reservationRepository.findAll().stream().map(ReservationDto :: forOccupiedSeat).collect(Collectors.toList());
    }
}
