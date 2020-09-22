package com.JPA.JPARepository.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public void setRelationWithTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }

    @Builder
    public Member(String username, int age){
        this.username = username;
        this.age = age;
    }
}
