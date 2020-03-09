package org.devs.teamcoll.Teamcoll.service;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.domain.*;
import org.devs.teamcoll.Teamcoll.dto.post.PostResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final PostRepository postRepository;
    private final CrewRepository crewRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public List<PostResponseDto> getAllPost(Long teamId) {
        List<PostResponseDto> posts = new ArrayList<>();

        teamRepository.getOne(teamId).getPosts()
                .forEach(post -> {
                    posts.add(
                            PostResponseDto.builder()
                            .id(post.getId())
                            .title(post.getTitle())
                            .username(post.getUsername())
                            .content(post.getContent())
                            .build()
                    );
                });

        return posts;
    }
}
