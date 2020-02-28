package org.devs.teamcoll.Teamcoll.post;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.message.request.post.PostRegisterRequest;
import org.devs.teamcoll.Teamcoll.message.request.post.PostUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public List<Post> getAllPost(Long tid) {
        return postRepository.getPostsByIdOrderByCreatedDate(tid);
    }

    @Transactional
    public Post getPostById(Long pid) {
        return postRepository.getPostById(pid);
    }

    @Transactional
    public Post updatePost(Long pid, PostUpdateRequest updateRequest) {
        Post post = postRepository.getPostById(pid);
        return post.update(updateRequest);
    }

    @Transactional
    public Long save(PostRegisterRequest registerRequest) {
       return registerRequest.toPostEntity().getId();
    }
}
