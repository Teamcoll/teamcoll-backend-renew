package org.devs.teamcoll.Teamcoll.api;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.message.request.user.LoginForm;
import org.devs.teamcoll.Teamcoll.message.request.user.SignUpForm;
import org.devs.teamcoll.Teamcoll.repository.RoleRepository;
import org.devs.teamcoll.Teamcoll.repository.UserRepository;
import org.devs.teamcoll.Teamcoll.security.jwt.JwtProvider;
import org.devs.teamcoll.Teamcoll.security.jwt.JwtResponse;
import org.devs.teamcoll.Teamcoll.user.Role;
import org.devs.teamcoll.Teamcoll.user.RoleName;
import org.devs.teamcoll.Teamcoll.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt, loginRequest.getUsername()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(userRole);

        User newUser = User.builder()
                .username(signUpRequest.getUsername())
                .name(signUpRequest.getName())
                .password( encoder.encode(signUpRequest.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(newUser);

        return new ResponseEntity<>(newUser , HttpStatus.CREATED);
    }
}
