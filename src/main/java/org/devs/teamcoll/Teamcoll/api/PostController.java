package org.devs.teamcoll.Teamcoll.api;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.dto.post.PostRegisterFormRequestDto;
import org.devs.teamcoll.Teamcoll.dto.post.PostResponseDto;
import org.devs.teamcoll.Teamcoll.dto.post.PostUpdateFormRequestDto;
import org.devs.teamcoll.Teamcoll.service.PostService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("{teamId}/post/register")
    public ResponseEntity<Long> registerPost(@PathVariable("teamId") Long id,
                                             @RequestBody PostRegisterFormRequestDto postRegisterFormRequestDto) {
        return new ResponseEntity<>(postService.registerPost(id, postRegisterFormRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("{teamId}/post/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable("teamId") Long teamId,
                                                   @PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @PutMapping("{teamId}/post/{postId}")
        public ResponseEntity<PostResponseDto> updatePost(@PathVariable("teamId") Long teamId,
                                                      @PathVariable("postId") Long postId,
                                                      @RequestBody PostUpdateFormRequestDto postUpdateFormRequestDto) {
        return ResponseEntity.ok(postService.updatePost(postId, postUpdateFormRequestDto));
    }

    @DeleteMapping("{teamId}/post/{postId}")
    public ResponseEntity<Object> deletePost(@PathVariable("teamId") Long teamId,
                                     @PathVariable("postId") Long postId) throws URISyntaxException {
       postService.deletePost(postId);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("http://127.0.0.1:8080"));
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }
}
