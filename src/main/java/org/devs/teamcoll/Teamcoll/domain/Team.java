package org.devs.teamcoll.Teamcoll.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;

    @Column(columnDefinition = "BLOB")
    private String description;

    @OneToMany(mappedBy = "team")
    List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    List<Todo> todoList = new ArrayList<>();


    @Builder
    public Team(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
