package org.devs.teamcoll.Teamcoll.team;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.teamcoll.Teamcoll.model.BaseTimeEntity;
import org.devs.teamcoll.Teamcoll.post.Post;
import org.devs.teamcoll.Teamcoll.todo.Todo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Team extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(name = "TEAM_NAME", nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    List<Crew> crews = new ArrayList<>();

    @OneToMany
    List<Post> posts = new ArrayList<>();

    @OneToMany
    List<Todo> todoList = new ArrayList<>();

    @Builder
    public Team(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
