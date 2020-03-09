package org.devs.teamcoll.Teamcoll.api;

import lombok.RequiredArgsConstructor;
import org.devs.teamcoll.Teamcoll.domain.User;
import org.devs.teamcoll.Teamcoll.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable("userId") Long id,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        userService.deleteUserById(id);
        response.sendRedirect("/");
    }
}
