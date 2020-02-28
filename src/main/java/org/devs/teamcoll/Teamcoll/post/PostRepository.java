package org.devs.teamcoll.Teamcoll.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p join Team t where p.team.id = ?1")
    List<Post> getPostsByIdOrderByCreatedDate(Long id);

    Post getPostById(Long id);
}
