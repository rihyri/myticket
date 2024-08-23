package jpastore.jpaticket.service;

import ch.qos.logback.core.spi.ErrorCodes;
import jakarta.persistence.EntityManager;
import jpastore.jpaticket.domain.Game;
import jpastore.jpaticket.repository.GameRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GameService {

    @Autowired
    private final GameRepository gameRepository;


    public void create(String title, String gameDate, String gameTime, int price, String redTeam, String blueTeam, String place) {
        Game game = new Game();
        game.setTitle(title);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(gameDate, formatter);
        game.setGameDate(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        
        // 시간도 바꿔주기
        String formatter2 = String.format("%04d", Integer.parseInt(gameTime));
        StringBuilder sb = new StringBuilder();
        sb.append(formatter2);
        sb.insert(formatter2.length() / 2, ":");
        game.setGameTime(sb.toString());

        game.setPrice(price);
        game.setRedTeam(redTeam);
        game.setBlueTeam(blueTeam);
        game.setPlace(place);

        this.gameRepository.save(game);
    }

    public Game getGame(Long id) throws Exception {
        return this.gameRepository.findById(id).orElseThrow(() -> new Exception("Game not found with id: " + id));
    }
}
