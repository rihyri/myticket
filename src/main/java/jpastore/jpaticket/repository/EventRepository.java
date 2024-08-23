package jpastore.jpaticket.repository;

import jpastore.jpaticket.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    Event findBySubject(String subject);
    Event findBySubjectAndContent(String subject, String content);
    List<Event> findBySubjectLike(String subject);
    Page<Event> findAll(Pageable pageable);
}
