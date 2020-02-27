package org.devs.teamcoll.Teamcoll.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.devs.teamcoll.Teamcoll.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;


@Getter
public class UserPrinciple implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String name;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    @Builder
    public UserPrinciple(Long id, String username, String name, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrinciple build(User user){

        return UserPrinciple.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .password(user.getPassword())
                .authorities(
                        user.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                            .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
