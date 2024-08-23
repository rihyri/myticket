package jpastore.jpaticket.service;

import jpastore.jpaticket.domain.Reservation;
import jpastore.jpaticket.dto.ReservationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {

    default Reservation dtoToEntity(ReservationDto reservationDto) {
        Reservation reservation = Reservation.builder().reservation_id(reservationDto.getReservation_id()).reservation_day(reservationDto.getReservation_day()).reservation_num(reservationDto.getReservation_num()).build();

        return reservation;
    }

    default ReservationDto entityToDto(Reservation reservation) {
        ReservationDto reservationDto = ReservationDto.builder().reservation_id(reservation.getReservation_id()).reservation_day(reservation.getReservation_day()).reservation_num(reservation.getReservation_num()).build();

        return reservationDto;
    }

    void processReservation(ReservationDto reservationDto);

    Reservation save (Reservation reservation);

    boolean cancelReservation(Long reservation_id);

    List<ReservationDto> findAllReservations();

}
