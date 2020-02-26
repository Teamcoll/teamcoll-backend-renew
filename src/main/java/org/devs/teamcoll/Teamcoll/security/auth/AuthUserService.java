package org.devs.teamcoll.Teamcoll.security.auth;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return AuthUser.build(
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new UsernameNotFoundException(String.format("%s is not founded!", email))));
    }
}
