package jpastore.jpaticket.repository;

import jpastore.jpaticket.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
