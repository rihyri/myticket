package jpastore.jpaticket.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jpastore.jpaticket.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private Long reservation_id;
    private String reservation_day;
    private String reservation_num;
    private String userName;
    private String title;
    private String redTeam;
    private String blueTeam;
    private String gameDate;

    public static ReservationDto fromReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setReservation_id(reservationDto.getReservation_id());
        reservationDto.setReservation_day(reservationDto.getReservation_day());
        reservationDto.setReservation_num(reservationDto.getReservation_num());
        reservationDto.setTitle(reservationDto.getTitle());
        reservationDto.setRedTeam(reservationDto.getRedTeam());
        reservationDto.setBlueTeam(reservationDto.getBlueTeam());
        reservationDto.setGameDate(reservation.getGameDate());

        return reservationDto;
    }

    public static ReservationDto forOccupiedSeat(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setReservation_num(reservationDto.getReservation_num());
        return reservationDto;
    }
}
