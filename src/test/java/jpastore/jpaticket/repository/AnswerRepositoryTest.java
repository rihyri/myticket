package jpastore.jpaticket.repository;

import jpastore.jpaticket.domain.Answer;
import jpastore.jpaticket.domain.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnswerRepositoryTest {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void answer_test() {
        Optional<Event> oe = this.eventRepository.findById(1);
        assertTrue(oe.isPresent());
        Event e = oe.get();

        Answer a = new Answer();
        a.setContent("이벤트 참여합니다.");
        a.setEvent(e);
        a.setCreateTime(LocalDateTime.now());
        this.answerRepository.save(a);
    }
}