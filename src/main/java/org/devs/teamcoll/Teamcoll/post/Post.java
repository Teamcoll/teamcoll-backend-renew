package org.devs.teamcoll.Teamcoll.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.teamcoll.Teamcoll.message.request.post.PostUpdateRequest;
import org.devs.teamcoll.Teamcoll.model.BaseTimeEntity;
import org.devs.teamcoll.Teamcoll.team.Team;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    private String title;

    private String author;

    private String content;

    @ManyToOne
    private Team team;

    @Builder
    public Post(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post update(PostUpdateRequest updateRequest) {
        return Post.builder()
                .title(updateRequest.getTitle())
                .author(author)
                .content(updateRequest.getContent())
                .build();
    }
}
