package jpastore.jpaticket.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jpastore.jpaticket.domain.Cart;
import jpastore.jpaticket.domain.Member;
import jpastore.jpaticket.domain.MemberForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional

class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    @Test
    public Member createMember() {
        MemberForm memberForm = new MemberForm();
        memberForm.setUserName("keria");
        memberForm.setEmail("keria@naver.com");
        memberForm.setPassword("keria1234^^");
        memberForm.setPassword2("keria1234^^");
        return Member.createMember(memberForm, passwordEncoder);
    }

    @Test
    void Entity_mapping_view() {
        Member member = createMember();
        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setMember(member);
        cartRepository.save(cart);

        em.flush();
        em.clear();

        Cart savedCart = cartRepository.findById(cart.getId()).orElseThrow(EntityNotFoundException::new);
        assertEquals(savedCart.getMember().getId(), member.getId());
    }
}