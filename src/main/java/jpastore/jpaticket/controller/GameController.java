package jpastore.jpaticket.controller;

import jpastore.jpaticket.domain.Game;
import jpastore.jpaticket.dto.GameDto;
import jpastore.jpaticket.repository.GameRepository;
import jpastore.jpaticket.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/games")
@SessionAttributes("gameList")
public class GameController {

    @Autowired
    private final GameRepository gameRepository;
    @Autowired
    private final GameService gameService;


    @GetMapping("/list")
    public String list() {
        return "game/get_game_list";
    }

    @PostMapping("/list")
    public ResponseEntity<String> list(@RequestBody List<GameDto> gameList, Model model) {
        model.addAttribute("gameList", gameList);
        return ResponseEntity.ok("/games/list2");
    }

    @GetMapping("/list2")
    public String list2(@ModelAttribute("gameList") List<GameDto> gameList, Model model) {
        model.addAttribute("gameList", gameList);
        return "game/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(
            Model model,
            @ModelAttribute("gameList") List<GameDto> gameList,
            @PathVariable("id") int id) {
        model.addAttribute("game", gameList.get(id));
        return "game/detail";
    }

}
