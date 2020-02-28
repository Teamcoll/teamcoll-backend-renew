package org.devs.teamcoll.Teamcoll.user;

import org.devs.teamcoll.Teamcoll.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    User getByUsername(String username);
}
