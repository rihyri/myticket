package jpastore.jpaticket.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jpastore.jpaticket.domain.MemberForm;
import jpastore.jpaticket.repository.MemberRepository;
import jpastore.jpaticket.security.SecurityConfig;
import jpastore.jpaticket.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final MemberService memberService;
    @Autowired
    private final SecurityConfig securityConfig;

    @GetMapping("/signup")
    public String signup(MemberForm memberForm) {
        return "signup";
    }

    @Operation(
            summary = "회원가입",
            description = "회원가입을 합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "회원가입에 성공하였습니다."
    )
    @PostMapping("/signup")
    public String signup(@Valid MemberForm memberForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "user/signup";
        }

        if(!memberForm.getPassword().equals(memberForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordIncorrect", "비밀번호가 일치하지 않습니다.");
            return "user/signup";
        }

        try {
            memberService.create(memberForm.getUserName(), memberForm.getPassword(), memberForm.getEmail());
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("userName", "Duplicate.userName", "이미 등록된 사용자입니다.");
            return "signup";
        } catch(Exception e) {
            bindingResult.rejectValue("userName", "Exception", e.getMessage());
            return "join";
        }

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
}
