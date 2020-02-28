package org.devs.teamcoll.Teamcoll.api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devs.teamcoll.Teamcoll.message.request.team.RegisterTeamForm;
import org.devs.teamcoll.Teamcoll.security.jwt.JwtAuthEntryPoint;
import org.devs.teamcoll.Teamcoll.security.jwt.JwtAuthTokenFilter;
import org.devs.teamcoll.Teamcoll.security.jwt.JwtProvider;
import org.devs.teamcoll.Teamcoll.security.service.UserDetailsServiceImpl;
import org.devs.teamcoll.Teamcoll.team.CrewService;
import org.devs.teamcoll.Teamcoll.team.TeamService;
import org.devs.teamcoll.Teamcoll.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipal;
import java.security.Key;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("team")
public class TeamController {

    private final TeamService teamService;
    private final CrewService crewService;
    private final UserService userService;
    private UserDetailsServiceImpl userDetailsService;


    @PostMapping("register")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Long> RegisterTeam(@Valid @RequestBody RegisterTeamForm form) {

        Long tid = teamService.save(form);



        return new ResponseEntity<>(
               tid , HttpStatus.CREATED
        );
    }

}
