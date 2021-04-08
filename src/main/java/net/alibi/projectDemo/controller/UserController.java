package net.alibi.projectDemo.controller;

import lombok.RequiredArgsConstructor;
import net.alibi.projectDemo.dto.UserDto;
import net.alibi.projectDemo.model.User;
import net.alibi.projectDemo.model.UserRole;
import net.alibi.projectDemo.model.enums.Role;
import net.alibi.projectDemo.repository.UserRepository;
import net.alibi.projectDemo.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @GetMapping
    public List<User> getAllTeachers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Error teacher with id "+ userId +" not found"));
    }

    @PostMapping
    public User createUser(@RequestBody UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        String strRoles = userDto.getRole();
        Set<UserRole> roles = new HashSet<>();
            switch (strRoles) {
                case "ROLE_ADMIN":
                    UserRole adminRole = userRoleRepository.findByRole(Role.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(adminRole);
                break;
                case "ROLE_MODERATOR":
                    UserRole modRole = userRoleRepository.findByRole(Role.ROLE_MODERATOR)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));roles.add(modRole);
                break;
                default:
                    UserRole userRole = userRoleRepository.findByRole(Role.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));roles.add(userRole);
            }

        User user = modelMapper.map(userDto, User.class);;
        user.setUserRoles(roles);
        return userRepository.save(user);
    }

    @PostMapping("/{id}")
    public User editUser(@PathVariable(value = "id") Long userId, @RequestBody UserDto userDto) {
        User rpUser = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Error teacher with id "+ userId +" not found"));
        rpUser.setFirstName(userDto.getFirstName());
        rpUser.setLastName(userDto.getLastName());
        rpUser.setStatus(userDto.getStatus());

        return userRepository.save(rpUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable (value = "id") Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Error teacher with id "+ userId +" not found" ));
        userRepository.delete(user);
        return ResponseEntity.ok().build();

    }
}
