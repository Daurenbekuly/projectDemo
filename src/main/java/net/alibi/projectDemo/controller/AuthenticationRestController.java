package net.alibi.projectDemo.controller;

import lombok.RequiredArgsConstructor;
import net.alibi.projectDemo.dto.JwtResponse;
import net.alibi.projectDemo.dto.UserDto;
import net.alibi.projectDemo.model.User;
import net.alibi.projectDemo.model.UserRole;
import net.alibi.projectDemo.model.enums.Role;
import net.alibi.projectDemo.repository.UserRepository;
import net.alibi.projectDemo.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@Valid @RequestBody UserDto userDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));
            User user = userRepository.findByUserName(userDto.getUserName())
                    .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
            String token = jwtTokenProvider.createToken(userDto.getUserName(), user.getUserRoles());

            Set<Role> roleSet = user.getUserRoles().stream().map(UserRole::getRole).collect(Collectors.toSet());
            return ResponseEntity.ok(new JwtResponse(
                    token,
                    user.getId(),
                    user.getUserName(),
                    user.getEmail(),
                    roleSet));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid username/password combination", HttpStatus.FORBIDDEN);
        }
    }
}
