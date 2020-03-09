package org.devs.teamcoll.Teamcoll.dto.user;

import lombok.AllArgsConstructor;
import org.devs.teamcoll.Teamcoll.domain.Team;
import org.devs.teamcoll.Teamcoll.domain.User;

@AllArgsConstructor
public class UserRegisterFormRequestDto {
    private String email;
    private String name;
    private String password;

    public User toUserEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();
    }
}
