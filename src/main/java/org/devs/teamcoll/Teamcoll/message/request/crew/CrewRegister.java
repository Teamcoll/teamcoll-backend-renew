package org.devs.teamcoll.Teamcoll.message.request.crew;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.teamcoll.Teamcoll.team.Crew;
import org.devs.teamcoll.Teamcoll.team.Team;
import org.devs.teamcoll.Teamcoll.user.User;

@Getter
@NoArgsConstructor
public class CrewRegister {

    private User user;
    private Team team;

    @Builder
    public CrewRegister(Team team, User user) {
        this.user = user;
        this.team = team;
    }

    public Crew toCrewEntity() {
        return Crew.builder()
                .team(team)
                .user(user)
                .build();
    }
}
