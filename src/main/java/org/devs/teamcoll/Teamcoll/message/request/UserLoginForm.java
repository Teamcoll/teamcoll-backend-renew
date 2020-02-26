package org.devs.teamcoll.Teamcoll.message.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserLoginForm {
    private final String username;
    private final String password;
}