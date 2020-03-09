package org.devs.teamcoll.Teamcoll.api;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.dto.team.TeamRegisterFormRequestDto;
import org.devs.teamcoll.Teamcoll.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("{userId}/team/register")
    public ResponseEntity<Long> registerTeam(@PathVariable("userId")  Long id,
                                             @RequestBody TeamRegisterFormRequestDto teamRegisterFormRequestDto) {
        return ResponseEntity.ok(userService.registerTeam(id, teamRegisterFormRequestDto));
    }


    @DeleteMapping("{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Long id) throws URISyntaxException {
        userService.deleteUserById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("http://127.0.0.1:8080"));
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }
}
