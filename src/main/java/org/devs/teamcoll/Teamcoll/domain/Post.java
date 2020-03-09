package org.devs.teamcoll.Teamcoll.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devs.teamcoll.Teamcoll.dto.post.PostUpdateFormRequestDto;
import org.devs.teamcoll.Teamcoll.model.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String username;

    private String content;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public Post(String title, String username, String content, Team team) {
        this.title = title;
        this.username = username;
        this.content = content;
        this.team = team;
    }

    public void update(PostUpdateFormRequestDto postUpdateFormRequestDto) {
        this.title = postUpdateFormRequestDto.getTitle();
        this.content = postUpdateFormRequestDto.getContent();
    }
}
