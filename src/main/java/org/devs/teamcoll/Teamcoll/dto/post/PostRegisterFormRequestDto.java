package org.devs.teamcoll.Teamcoll.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devs.teamcoll.Teamcoll.domain.Post;

@Setter
@Getter
@NoArgsConstructor
public class PostRegisterFormRequestDto {
    private String title;
    private String username;
    private String content;

    @Builder
    public PostRegisterFormRequestDto(String title, String username, String content) {
        this.title = title;
        this.username = username;
        this.content = content;
    }

    public Post toPostEntity() {
        return Post.builder()
                .title(title)
                .username(username)
                .content(content)
                .build();
    }
}
