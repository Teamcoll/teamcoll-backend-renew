package org.devs.teamcoll.Teamcoll.api;

import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestApi {

    @GetMapping("api/test/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userTest() {
        return "user test!";
    }

    @GetMapping("api/test/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminTest() {
        return "admin test!";
    }
}
