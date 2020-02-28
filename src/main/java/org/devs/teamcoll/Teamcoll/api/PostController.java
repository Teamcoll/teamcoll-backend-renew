package org.devs.teamcoll.Teamcoll.api;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.message.request.post.PostRegisterRequest;
import org.devs.teamcoll.Teamcoll.message.request.post.PostUpdateRequest;
import org.devs.teamcoll.Teamcoll.post.Post;
import org.devs.teamcoll.Teamcoll.post.PostService;
import org.devs.teamcoll.Teamcoll.team.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @GetMapping("team/{teamId}/posts")
    public ResponseEntity<List<Post>> getAllPosts(@PathVariable("teamId") Long id) {
        List<Post> posts = postService.getAllPost(id);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("team/{teamId}/post/{pid}")
    public ResponseEntity<Post> getPost(@PathVariable("teamId") Long tid, @PathVariable("pid") Long pid) {
        Post post = postService.getPostById(pid);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("team/{teamId}/post/{pid}")
    public ResponseEntity<Post> updatePost(@PathVariable("teamId") Long tid,
                           @PathVariable("pid") Long pid,
                           @RequestBody PostUpdateRequest updateRequest) {

        Post post = postService.updatePost(pid,updateRequest);
        return new ResponseEntity<>( HttpStatus.OK) ;
    }

    @PostMapping("team/{teamId}/post/register")
    public ResponseEntity<Long> registerPost(@PathVariable("teamId") Long tid, @RequestBody PostRegisterRequest registerRequest) {
        return new ResponseEntity<>(postService.save(registerRequest), HttpStatus.OK );
    }

    @DeleteMapping("team/post/{pid}/delete")
    public void deletePost(@PathVariable("pid") Long pid) {

    }
}
