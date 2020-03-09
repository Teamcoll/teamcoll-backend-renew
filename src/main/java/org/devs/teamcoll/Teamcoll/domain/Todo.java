package org.devs.teamcoll.Teamcoll.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.devs.teamcoll.Teamcoll.model.BaseTimeEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Todo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    private String title;

    private LocalDate start;

    private LocalDate end;

    @Column(columnDefinition = "BLOB")
    private String description;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public Todo(String title, LocalDate start, LocalDate end, String description, Team team) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.team = team;
    }
}
