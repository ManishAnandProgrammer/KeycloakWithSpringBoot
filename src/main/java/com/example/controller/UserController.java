package com.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Anonymous User";
    }

    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping("/hello/User")
    public String sayHello(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities =
                authentication.getAuthorities();

        List<String> collect = authorities.stream().map(GrantedAuthority::getAuthority).toList();

        return "Hello " + collect;

    }
}
