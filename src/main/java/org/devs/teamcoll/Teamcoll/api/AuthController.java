package org.devs.teamcoll.Teamcoll.api;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.domain.User;
import org.devs.teamcoll.Teamcoll.dto.user.UserLoginFormRequestDto;
import org.devs.teamcoll.Teamcoll.dto.user.UserRegisterFormRequestDto;
import org.devs.teamcoll.Teamcoll.dto.user.UserResponseDto;
import org.devs.teamcoll.Teamcoll.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<Long> registerUser(@RequestBody UserRegisterFormRequestDto register) {
        return new ResponseEntity<>(authService.registerUser(register), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<UserResponseDto> loginUser(@RequestBody UserLoginFormRequestDto loginForm,
                                                     HttpServletRequest request) {
        User loginUser = authService.getUserInfo(loginForm);

        if (loginUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("email", loginForm.getEmail());
        }

        assert loginUser != null;

        return new ResponseEntity<>(UserResponseDto.builder()
                .id(loginUser.getId())
                .email(loginUser.getEmail())
                .name(loginUser.getName())
                .build(),
                HttpStatus.ACCEPTED);
    }

    @GetMapping("logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok("Logout Success");
    }
}
