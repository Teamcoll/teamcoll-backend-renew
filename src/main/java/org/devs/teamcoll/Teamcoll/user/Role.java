package org.devs.teamcoll.Teamcoll.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @Builder
    public Role(RoleName name){
        this.name = name;
    }
}