package org.devs.teamcoll.Teamcoll.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.devs.teamcoll.Teamcoll.user.User;

@Setter
@Getter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private User user;

    public JwtResponse(String accessToken, User user) {
        this.token = accessToken;
        this.user = user;
    }
}
