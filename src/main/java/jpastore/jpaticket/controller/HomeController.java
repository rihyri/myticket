package jpastore.jpaticket.controller;

import jpastore.jpaticket.domain.Game;
import jpastore.jpaticket.domain.OrderItem;
import jpastore.jpaticket.domain.OrderStatus;
import jpastore.jpaticket.repository.GameRepository;
import jpastore.jpaticket.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final GameRepository gameRepository;
    private final GameService gameService;

    @GetMapping("/")
    public String home(Model model) {

        List<Game> gameList = gameRepository.findAll();
        model.addAttribute("gameList", gameList);

        return "home";
    }
}
