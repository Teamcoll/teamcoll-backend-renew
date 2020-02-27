package org.devs.teamcoll.Teamcoll.message.request.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class SignUpForm {
    private String username;
    private String name;
    private String password;
}
