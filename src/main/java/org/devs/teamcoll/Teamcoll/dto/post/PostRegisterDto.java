package org.devs.teamcoll.Teamcoll.dto.post;

import lombok.AllArgsConstructor;
import org.devs.teamcoll.Teamcoll.domain.Post;

@AllArgsConstructor
public class PostRegisterDto {
    private String title;
    private String username;
    private String content;

    public Post toPostEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .username(username)
                .build();
    }
}
