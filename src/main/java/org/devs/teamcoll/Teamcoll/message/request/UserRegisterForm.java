package org.devs.teamcoll.Teamcoll.message.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@ToString
public class UserRegisterForm {
    private String email;
    private String username;
    private Set<String> role;
    private String password;

    @Builder
    public UserRegisterForm(String email, String username, Set<String> role, String password) {
        this.email = email;
        this.username = username;
        this.role = role;
        this.password = password;
    }
}
