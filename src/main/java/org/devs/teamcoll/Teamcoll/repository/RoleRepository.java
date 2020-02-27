package org.devs.teamcoll.Teamcoll.repository;

import org.devs.teamcoll.Teamcoll.user.Role;
import org.devs.teamcoll.Teamcoll.user.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName name);
}
