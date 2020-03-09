package org.devs.teamcoll.Teamcoll.service;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.domain.*;
import org.devs.teamcoll.Teamcoll.dto.team.RegisterTeamDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CrewRepository crewRepository;
    private final TeamRepository teamRepository;

    public Long registerTeam(Long userId, RegisterTeamDto registerTeam) {
        User user = userRepository.getUserById(userId);
        Long teamId = teamRepository.save(registerTeam.toTeamEntity()).getId();

        crewRepository.save( Crew.builder()
                .crewname(user.getName())
                .user(user)
                .team(teamRepository.getOne(teamId))
                .teaname(registerTeam.getName())
                .build()
        );

        return teamId;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
