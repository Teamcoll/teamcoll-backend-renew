package org.devs.teamcoll.Teamcoll.dto.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.devs.teamcoll.Teamcoll.domain.Team;

@Getter
@AllArgsConstructor
public class RegisterTeamDto {
    private String name;
    private String description;

    public Team toTeamEntity() {
        return Team.builder()
                .name(name)
                .description(description)
                .build();
    }
}
