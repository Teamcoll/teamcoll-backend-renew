package org.devs.teamcoll.Teamcoll.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
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

    @Builder
    public User(Long id, String username,
                String name, String password,
                Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

}
