package org.devs.teamcoll.Teamcoll.message.request.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.teamcoll.Teamcoll.post.Post;

@Getter
@NoArgsConstructor
public class PostUpdateRequest {
    private String title;
    private String content;


    @Builder
    public PostUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
