package org.devs.teamcoll.Teamcoll.security.service;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.user.UserRepository;
import org.devs.teamcoll.Teamcoll.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found")
                );

        return UserPrinciple.build(user);

    }
}
