package jpastore.jpaticket.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Test
    void testJpa(){
        for (int i=1; i<=10; i++) {
            String subject = String.format("테스트 데이터입니다.:[%03d]", i);
            String content = "내용없음";
            this.eventService.create(subject, content, null);
        }
    }
}