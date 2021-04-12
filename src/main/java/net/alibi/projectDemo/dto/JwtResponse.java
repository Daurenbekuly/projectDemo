package net.alibi.projectDemo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.alibi.projectDemo.model.enums.Role;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class JwtResponse {
    private final String accessToken;
    private final Long id;
    private final String username;
    private final String email;
    private final Set<Role> roles;
}
