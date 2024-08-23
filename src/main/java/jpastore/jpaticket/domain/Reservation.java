package jpastore.jpaticket.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservation_id;    // 일련번호

    @Column(nullable = false)
    private String reservation_day; // 예매날짜

    @Column(nullable = false)
    private String reservation_num; // 에매번호

    @Column(name="cid")
    private String userName;
    private String gameDate;
    private String title;   // 게임 이름
    private String redTeam;
    private String blueTeam;
}
