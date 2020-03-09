package org.devs.teamcoll.Teamcoll.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginFormRequestDto {
    private String email;
    private String password;
}
