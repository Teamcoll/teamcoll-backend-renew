package org.devs.teamcoll.Teamcoll.message.request.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.teamcoll.Teamcoll.post.Post;

@Getter
@NoArgsConstructor
public class PostRegisterRequest {
    private String title;
    private String author;
    private String content;

    @Builder
    public PostRegisterRequest(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post toPostEntity() {
        return Post.builder()
                .title(title)
                .author(author)
                .content(content)
                .build();
    }
}
