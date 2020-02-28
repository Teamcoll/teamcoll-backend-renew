package org.devs.teamcoll.Teamcoll.team;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.message.request.team.RegisterTeamForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;

    @Transactional
    public Long save(RegisterTeamForm form) {
        return teamRepository.save(form.teamEntity()).getId();
    }
}
