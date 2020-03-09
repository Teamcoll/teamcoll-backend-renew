package org.devs.teamcoll.Teamcoll.service;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.domain.User;
import org.devs.teamcoll.Teamcoll.domain.UserRepository;
import org.devs.teamcoll.Teamcoll.dto.user.UserLoginFormRequestDto;
import org.devs.teamcoll.Teamcoll.dto.user.UserRegisterFormRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    @Transactional
    public Long registerUser(UserRegisterFormRequestDto registerForm) {
        return userRepository.save(registerForm.toUserEntity()).getId();
    }

    @Transactional
    public User getUserInfo(UserLoginFormRequestDto loginForm) {
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();

        return userRepository.getUserByEmailAndPassword(email, password)
                .orElse(null);
    }
}
