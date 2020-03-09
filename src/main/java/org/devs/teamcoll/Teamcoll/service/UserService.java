package org.devs.teamcoll.Teamcoll.service;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.domain.*;
import org.devs.teamcoll.Teamcoll.dto.team.TeamRegisterFormRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CrewRepository crewRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public void deleteUserById(Long id) { userRepository.deleteById(id);}

    @Transactional
    public Long registerTeam(Long userId,
                             TeamRegisterFormRequestDto teamRegisterFormRequestDto) {
        User currentUser = userRepository.getOne(userId);
        Team newTeam = teamRepository.save(teamRegisterFormRequestDto.toTeamEntity());

        crewRepository.save(
                Crew.builder()
                .teamname(newTeam.getName())
                .team(newTeam)
                .user(currentUser)
                .crewname(currentUser.getName())
                .build()
        );

        return newTeam.getId();
    }
}
