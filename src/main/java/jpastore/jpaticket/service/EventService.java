package jpastore.jpaticket.service;

import jpastore.jpaticket.DataNotFoundException;
import jpastore.jpaticket.domain.Event;
import jpastore.jpaticket.domain.Member;
import jpastore.jpaticket.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> getList() {
        return this.eventRepository.findAll();
    }

    public Event getEvent(Integer id) {
        Optional<Event> event = this.eventRepository.findById(id);
        if (event.isPresent()) {
            return event.get();
        } else {
            throw new DataNotFoundException("event not found");
        }
    }

    public void create(String subject, String content, Member member) {
        Event e = new Event();
        e.setSubject(subject);
        e.setContent(content);
        e.setAuthor(member);
        e.setCreateDate(LocalDateTime.now());
        this.eventRepository.save(e);
    }

    public Page<Event> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.eventRepository.findAll(pageable);
    }

    public void modify(Event event, String subject, String content) {
        event.setSubject(subject);
        event.setContent(content);
        event.setModifyDate(LocalDateTime.now());
        this.eventRepository.save(event);
    }

    public void delete(Event event) {
        this.eventRepository.delete(event);
    }
}
