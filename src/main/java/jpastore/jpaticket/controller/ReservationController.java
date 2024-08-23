package jpastore.jpaticket.controller;

import jakarta.servlet.http.HttpServletRequest;
import jpastore.jpaticket.domain.Member;
import jpastore.jpaticket.domain.Reservation;
import jpastore.jpaticket.repository.ReservationRepository;
import jpastore.jpaticket.service.GameService;
import jpastore.jpaticket.service.MemberService;
import jpastore.jpaticket.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/reserve")
public class ReservationController {

    private final ReservationService reservationService;
    private final GameService gameService;
    private final MemberService memberService;

    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationController (
            ReservationService reservationService,
            GameService gameService,
            MemberService memberService
    ) {
        this.reservationService = reservationService;
        this.gameService = gameService;
        this.memberService = memberService;
    }

    @GetMapping(value = "/isAuthenticated")
    @ResponseBody
    public boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated();
    }

    @GetMapping("/start")
    public String startGET(
            @RequestParam("title") String title,
            @RequestParam("redTeam") String redTeam,
            @RequestParam("blueTeam") String blueTeam,
            @RequestParam("gameDate") String gameDate,
            Model model,
            HttpServletRequest request
    ) {
        Object principal = request.getUserPrincipal();
        String username = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : null;
        model.addAttribute("userName", username);

        List<String> reservedSeats = reservationRepository.findReservationSeats(title);
        model.addAttribute("reservedSeats", reservedSeats);

        model.addAttribute("title", title);
        model.addAttribute("redTeam", redTeam);
        model.addAttribute("blueTeam", blueTeam);
        model.addAttribute("gameDate", gameDate);
        return "reserve/start";
    }

    @PostMapping("/start")
    public String startPOST(HttpServletRequest httpServletRequest, Model model) {
        String reservation_day = (httpServletRequest.getParameter("reservation_day") != null) ? httpServletRequest.getParameter("reservation_day") : "";
        String reservation_num = (httpServletRequest.getParameter("reservation_num") != null) ? httpServletRequest.getParameter("reservation_num") : "";
        String title = httpServletRequest.getParameter("title");
        String redTeam = httpServletRequest.getParameter("redTeam");
        String blueTeam = httpServletRequest.getParameter("blueTeam");
        String gameDate = httpServletRequest.getParameter("gameDate");
        List<String> reservedSeats = reservationRepository.findReservationSeats(title);

        model.addAttribute("title", title);
        model.addAttribute("redTeam", redTeam);
        model.addAttribute("blueTeam", blueTeam);
        model.addAttribute("gameDate", gameDate);
        model.addAttribute("reservedSeats", reservedSeats);

        return "reserve/start";
    }

    @GetMapping("/check")
    public String checkGET(
            @RequestParam("title") String title,
            @RequestParam("redTeam") String redTeam,
            @RequestParam("blueTeam") String blueTeam,
            @RequestParam("gameDate") String gameDate,
            @RequestParam("reservation_day") String reservation_day,
            @RequestParam("reservation_num") String reservation_num,
            Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("redTeam", redTeam);
        model.addAttribute("blueTeam", blueTeam);
        model.addAttribute("gameDate", gameDate);
        model.addAttribute("reservation_day", reservation_day);
        model.addAttribute("reservation_num", reservation_num);
        return "reserve/check";
    }

    @PostMapping("/check")
    public String checkPOST(
            @RequestParam("title") String title,
            @RequestParam("redTeam") String redTeam,
            @RequestParam("blueTeam") String blueTeam,
            @RequestParam("gameDate") String gameDate,
            @RequestParam("reservation_day") String reservation_day,
            @RequestParam("reservation_num") String reservation_num,
            Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("redTeam", redTeam);
        model.addAttribute("blueTeam", blueTeam);
        model.addAttribute("gameDate", gameDate);
        model.addAttribute("reservation_day", reservation_day);
        model.addAttribute("reservation_num", reservation_num);
        return "reserve/check";
    }

    @PostMapping("/done")
    public String donePOST(
            @RequestParam("username") String username,
            @RequestParam("title") String title,
            @RequestParam("redTeam") String redTeam,
            @RequestParam("blueTeam") String blueTeam,
            @RequestParam("gameDate") String gameDate,
            @RequestParam("reservation_day") String reservation_day,
            @RequestParam("reservation_num") String reservation_num
    ) {
        Reservation reservation = new Reservation();
        reservation.setUserName(username);
        reservation.setReservation_day(reservation_day);
        reservation.setReservation_num(reservation_num);
        reservation.setTitle(title);
        reservation.setRedTeam(redTeam);
        reservation.setBlueTeam(blueTeam);
        reservation.setGameDate(gameDate);
        Reservation saveReserve = reservationService.save(reservation);
        return "reserve/done";
    }
}
