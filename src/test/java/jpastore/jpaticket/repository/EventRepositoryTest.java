package jpastore.jpaticket.repository;

import jpastore.jpaticket.domain.Answer;
import jpastore.jpaticket.domain.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void event_test() {
        Event e1 = new Event();
        e1.setSubject("이벤트1");
        e1.setContent("이벤트 예시입니다.");
        e1.setCreateDate(LocalDateTime.now());
        this.eventRepository.save(e1);

        Event e2 = new Event();
        e2.setSubject("이벤트2");
        e2.setContent("이벤트 예시 두번째 입니다.");
        e2.setCreateDate(LocalDateTime.now());
        this.eventRepository.save(e2);
    }

    @Transactional
    @Test
    void event_test2() {
        Optional<Event> oe = this.eventRepository.findById(1);
        assertTrue(oe.isPresent());
        Event e = oe.get();

        List<Answer> answerList = e.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("이벤트 참여합니다.", answerList.get(0).getContent());
    }
}