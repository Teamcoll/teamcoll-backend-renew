package org.devs.teamcoll.Teamcoll.api;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.dto.post.PostResponseDto;
import org.devs.teamcoll.Teamcoll.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TeamController {
    private final TeamService teamService;

    @GetMapping("{teamId}/posts")
    public ResponseEntity<List<PostResponseDto>> getAllPosts(@PathVariable("teamId") Long id) {
        return ResponseEntity.ok(teamService.getAllPost(id));
    }


}
