package org.devs.teamcoll.Teamcoll.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devs.teamcoll.Teamcoll.team.Crew;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    //EMAIL
    private String username;

    private String name;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    Set<Role> roles = new HashSet<>();

    @OneToMany
    private List<Crew> belong = new ArrayList<>();

    @Builder
    public User(Long id, String username,
                String name, String password,
                Set<Role> roles, List<Crew> belong) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.belong = belong;
    }

}
