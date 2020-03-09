package org.devs.teamcoll.Teamcoll.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter @Getter
@NoArgsConstructor
public class UserResponseDto implements Serializable {
    private Long id;
    private String email;
    private String name;

    @Builder
    public UserResponseDto(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
