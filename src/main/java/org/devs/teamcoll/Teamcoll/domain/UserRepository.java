package org.devs.teamcoll.Teamcoll.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    void deleteById(Long aLong);
    Optional<User> getUserByEmailAndPassword(String email, String password);
    User getUserById(Long id);
}
