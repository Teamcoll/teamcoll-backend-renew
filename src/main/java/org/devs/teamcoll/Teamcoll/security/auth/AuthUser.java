package org.devs.teamcoll.Teamcoll.security.auth;

import lombok.Builder;
import org.devs.teamcoll.Teamcoll.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AuthUser implements UserDetails {

    private Long id;
    private Collection<? extends  GrantedAuthority> authorities;
    private String email;
    private String username;
    private String password;
    private Boolean isAccountNonExpired = true;
    private Boolean isAccountNonLocked = true;
    private Boolean isCredentialsNonExpired = true;
    private Boolean isEnabled = true;

    @Builder
    public AuthUser(Long id,
                    Collection<? extends GrantedAuthority> authorities,
                    String email,
                    String username,
                    String password) {
        this.id = id;
        this.authorities = authorities;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public static AuthUser build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return AuthUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
    public Long getId(){
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
