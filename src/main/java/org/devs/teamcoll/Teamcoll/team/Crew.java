package org.devs.teamcoll.Teamcoll.team;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.teamcoll.Teamcoll.model.BaseTimeEntity;
import org.devs.teamcoll.Teamcoll.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "CREW")
public class Crew extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CREW_ID")
    private Long id;

    @Column(name = "CREW_NAME")
    private String name;

    @ManyToOne
    private User user;

    @ManyToOne
    private Team team;

    @Builder
    public Crew(User user, Team team) {
        this.name = user.getName();
        this.user = user;
        this.team = team;
    }


}
