package org.devs.teamcoll.Teamcoll.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.teamcoll.Teamcoll.model.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Crew extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crew_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "crew_name")
    private String crewname;

    private String email;

    @Column(name = "team_name")
    private String teamname;

    @Builder
    public Crew(User user, Team team, String crewname, String teamname, String email) {
        this.user = user;
        this.team = team;
        this.crewname = crewname;
        this.teamname = teamname;
        this.email = email;
    }
}
