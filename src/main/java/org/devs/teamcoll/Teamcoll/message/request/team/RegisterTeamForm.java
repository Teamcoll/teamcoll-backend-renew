package org.devs.teamcoll.Teamcoll.message.request.team;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.teamcoll.Teamcoll.team.Team;

@Getter
@NoArgsConstructor
public class RegisterTeamForm {
    private String name;
    private String  description;

    @Builder()
    public RegisterTeamForm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Team teamEntity() {
        return Team.builder()
                .name(name)
                .description(description)
                .build();
    }
}
