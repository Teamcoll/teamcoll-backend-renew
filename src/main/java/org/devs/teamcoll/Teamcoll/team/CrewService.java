package org.devs.teamcoll.Teamcoll.team;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.message.request.crew.CrewRegister;
import org.devs.teamcoll.Teamcoll.user.User;
import org.devs.teamcoll.Teamcoll.user.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CrewService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final CrewRepository crewRepository;


    public void save(String username, Long tid) {
        User u = userRepository.getByUsername(username);

        Team t = teamRepository.getTeamsById(tid);

        CrewRegister crew = new CrewRegister(t,u);

        crewRepository.save(crew.toCrewEntity());
    }
}
