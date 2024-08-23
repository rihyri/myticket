package jpastore.jpaticket.service;

import jpastore.jpaticket.DataNotFoundException;
import jpastore.jpaticket.domain.Member;
import jpastore.jpaticket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member create(String userName, String password, String email) {

        Member member = new Member();
        member.setUserName(userName);

        member.setPassword(passwordEncoder.encode(password));
        member.setEmail(email);

        this.memberRepository.save(member);
        return member;
    }

    public Member getMember(String userName) {
        Optional<Member> member = this.memberRepository.findByUserName(userName);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }
}
