package org.devs.teamcoll.Teamcoll.service;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.domain.Post;
import org.devs.teamcoll.Teamcoll.domain.PostRepository;
import org.devs.teamcoll.Teamcoll.domain.TeamRepository;
import org.devs.teamcoll.Teamcoll.dto.post.PostRegisterFormRequestDto;
import org.devs.teamcoll.Teamcoll.dto.post.PostResponseDto;
import org.devs.teamcoll.Teamcoll.dto.post.PostUpdateFormRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public Long registerPost(Long teamId, PostRegisterFormRequestDto postRegisterFormRequestDto) {
        return postRepository.save(Post.builder()
            .title(postRegisterFormRequestDto.getTitle())
            .username(postRegisterFormRequestDto.getUsername())
            .content(postRegisterFormRequestDto.getContent())
            .team(teamRepository.getOne(teamId))
            .build()).getId();
    }

    @Transactional
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.getOne(id);

        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .username(post.getUsername())
                .content(post.getContent())
                .build();
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostUpdateFormRequestDto postUpdateFormRequestDto) {
        Post updatePost = postRepository.getOne(id);
        updatePost.update(postUpdateFormRequestDto);
        return PostResponseDto.builder()
                .id(id)
                .title(updatePost.getTitle())
                .username(updatePost.getUsername())
                .content(updatePost.getContent())
                .build();
    }

    @Transactional
    public void deletePost(Long id) {
        teamRepository.deleteById(id);
    }


}
