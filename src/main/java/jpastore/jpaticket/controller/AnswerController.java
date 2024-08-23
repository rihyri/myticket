package jpastore.jpaticket.controller;

import jakarta.validation.Valid;
import jpastore.jpaticket.domain.Answer;
import jpastore.jpaticket.domain.AnswerForm;
import jpastore.jpaticket.domain.Event;
import jpastore.jpaticket.domain.Member;
import jpastore.jpaticket.service.AnswerService;
import jpastore.jpaticket.service.EventService;
import jpastore.jpaticket.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final EventService eventService;
    private final AnswerService answerService;
    private final MemberService memberService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/reply/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
         Event event = this.eventService.getEvent(id);
         Member member = this.memberService.getMember(principal.getName());

         if(bindingResult.hasErrors()) {
             model.addAttribute("event", event);
             return "event/event_detail";
         }
         this.answerService.create(event, answerForm.getContent(), member);
         return String.format("redirect:/event/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String answerModfiy(AnswerForm answerForm, @PathVariable("Id") Integer id, Principal principal) {
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUserName().equals(principal.getName())) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

        answerForm.setContent(answer.getContent());
        return "event/answer_form";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String answerDelete(Principal principal, @PathVariable("id") Integer id) {
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUserName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }

        this.answerService.delete(answer);
        return String.format("redirect:/event/detail/%s", answer.getEvent().getId());
    }
}
