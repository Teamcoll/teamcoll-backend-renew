package org.devs.teamcoll.Teamcoll.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found!"));
    }
}
