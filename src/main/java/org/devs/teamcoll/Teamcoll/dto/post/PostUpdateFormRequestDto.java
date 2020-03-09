package org.devs.teamcoll.Teamcoll.dto.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostUpdateFormRequestDto {
    private Long id;
    private String title;
    private String username;
    private String content;

    @Builder
    public PostUpdateFormRequestDto(Long id, String title, String username, String content) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
    }
}
