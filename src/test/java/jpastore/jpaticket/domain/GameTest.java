package jpastore.jpaticket.domain;

import jpastore.jpaticket.repository.GameRepository;
import jpastore.jpaticket.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameTest {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameService gameService;

    @Test
    void game_data () {
        gameService.create("2024 LCK SPRING DRX vs NS RedForce", "20240506", "730", 15000, "Gen.G", "T1", "LCK 아레나 (그랑서울 3F, 롤파크)");
    }
}