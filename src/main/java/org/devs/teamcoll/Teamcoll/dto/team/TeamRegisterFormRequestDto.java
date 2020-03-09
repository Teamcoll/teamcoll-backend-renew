package org.devs.teamcoll.Teamcoll.dto.team;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devs.teamcoll.Teamcoll.domain.Team;

@Setter
@Getter
@NoArgsConstructor
public class TeamRegisterFormRequestDto {
    private String name;
    private String description;

    @Builder
    public TeamRegisterFormRequestDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Team toTeamEntity() {
        return Team.builder()
                .name(name)
                .description(description)
                .build();
    }
}
