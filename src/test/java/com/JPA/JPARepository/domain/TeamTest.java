package com.JPA.JPARepository.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class TeamTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void 팀회원_생성(){
        Team team = Team.builder()
                .name("테스트 팀")
                .build();
        em.persist(team);

        Member member1 = creeate_member("테스트 멤버", 15);
        Member member2 = creeate_member("테스트 멤버", 15);

        member1.setRelationWithTeam(team);
        member2.setRelationWithTeam(team);

        em.flush();
        em.clear();

        List<Member> findMembers = em.createQuery("select m from Member m", Member.class).getResultList();
        findMembers.forEach(member -> System.out.println(member.getAge() + ", " + member.getUsername() + ", " + member.getTeam().getName()));

    }

    private Member creeate_member(String username, int age) {
        Member member = Member.builder()
                .age(age)
                .username(username)
                .build();
        em.persist(member);

        return member;
    }

}