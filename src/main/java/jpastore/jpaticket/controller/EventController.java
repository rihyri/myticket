package jpastore.jpaticket.controller;

import jakarta.validation.Valid;
import jpastore.jpaticket.domain.AnswerForm;
import jpastore.jpaticket.domain.Event;
import jpastore.jpaticket.domain.EventForm;
import jpastore.jpaticket.domain.Member;
import jpastore.jpaticket.repository.EventRepository;
import jpastore.jpaticket.service.EventService;
import jpastore.jpaticket.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private final EventRepository eventRepository;
    @Autowired
    private final EventService eventService;
    @Autowired
    private final MemberService memberService;

    @GetMapping("/list")
    public String eventList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Event> paging = this.eventService.getList(page);
        int pageLimit = 5;
        int nowPage = paging.getPageable().getPageNumber()+1;
        int startPage = Math.max(((nowPage-1)/pageLimit)*pageLimit+1, 1);
        int endPage = Math.min(startPage + pageLimit-1, paging.getTotalPages());

        model.addAttribute("paging", paging);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "event/event_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Event event = this.eventService.getEvent(id);
        model.addAttribute("event", event);
        return "event/event_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String eventCreate(EventForm eventForm) {
        return "event/create";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid EventForm eventForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "event/create";
        }
        Member member = this.memberService.getMember(principal.getName());
        this.eventService.create(eventForm.getSubject(), eventForm.getContent(), member);
        return "redirect:/event/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String eventModify(EventForm eventForm, @PathVariable("id") Integer id, Principal principal) {
        Event event = this.eventService.getEvent(id);
        if(!event.getAuthor().getUserName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        eventForm.setSubject(event.getSubject());
        eventForm.setContent(event.getContent());
        return "event/create";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String eventModify(@Valid EventForm eventForm, BindingResult bindingResult, Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "event/create";
        }
        Event event = this.eventService.getEvent(id);
        if (!event.getAuthor().getUserName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        this.eventService.modify(event, eventForm.getSubject(), eventForm.getContent());
        return String.format("redirect:/event/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id")Integer id) {
        Event event = this.eventService.getEvent(id);
        if (!event.getAuthor().getUserName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.eventRepository.delete(event);
        return "redirect:/";
    }


}
