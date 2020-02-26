package org.devs.teamcoll.Teamcoll.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devs.teamcoll.Teamcoll.message.request.UserLoginForm;
import org.devs.teamcoll.Teamcoll.message.request.UserRegisterForm;
import org.devs.teamcoll.Teamcoll.security.jwt.JwtProvider;
import org.devs.teamcoll.Teamcoll.security.jwt.JwtResponse;
import org.devs.teamcoll.Teamcoll.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;



    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegisterForm signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        log.info("REGISTER FORM : {}", signUpRequest);

        // Creating user's account

        Set<String> strRoles = signUpRequest.getRole();
        log.info("ROLES : {} ", strRoles);
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
                    log.info("ROLE : {}", role);
                    if (role.equals("admin")) {
                        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                        roles.add(adminRole);
                    }

                    if (role.equals("user")) {
                        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                        roles.add(userRole);
                    }
                }
        );

        User user = User.builder()
                .email(signUpRequest.getEmail())
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok().body("User registered successfully!");
    }
}
