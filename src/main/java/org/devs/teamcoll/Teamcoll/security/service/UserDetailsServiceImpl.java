package org.devs.teamcoll.Teamcoll.security.service;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.repository.UserRepository;
import org.devs.teamcoll.Teamcoll.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found")
                );


        return UserPrinciple.build(user);

    }
}
