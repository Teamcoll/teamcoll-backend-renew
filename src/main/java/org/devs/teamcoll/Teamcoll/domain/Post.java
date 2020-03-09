package org.devs.teamcoll.Teamcoll.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.teamcoll.Teamcoll.model.BaseTimeEntity;

import javax.persistence.*;

@Getter
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
    public Post(String title, String username, String content) {
        this.title = title;
        this.username = username;
        this.content = content;
    }
}
