package jpastore.jpaticket.controller;

import jpastore.jpaticket.domain.Member;
import jpastore.jpaticket.domain.Reservation;
import jpastore.jpaticket.repository.MemberRepository;
import jpastore.jpaticket.service.MemberService;
import jpastore.jpaticket.service.MyReserveService;
import jpastore.jpaticket.service.ReservationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/my")
public class MyReserveController {

    @Autowired
    private MyReserveService myReserveService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/myReserve")
    public String findAllReservation(Principal principal, Model model) {
        List<Reservation> reservations = myReserveService.findAllReservation(principal);
        model.addAttribute("reservations", reservations);
        return "my/myReserve";
    }

    // 예매 취소
    @DeleteMapping("/myReserve/{reservation_id}")
    public ResponseEntity<?> cancelReservation(@PathVariable("reservation_id") Long reservationId) {
        boolean isCancelled = reservationService.cancelReservation(reservationId);

        if (isCancelled) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/myPage")
    public String findMyPage(Principal principal, Model model) {
        Member member = memberRepository.findByUserName(principal.getName()).get();
        model.addAttribute("member", member);
        return "my/myPage";
    }
}
