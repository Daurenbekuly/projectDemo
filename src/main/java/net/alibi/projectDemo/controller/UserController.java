package net.alibi.projectDemo.controller;

import lombok.RequiredArgsConstructor;
import net.alibi.projectDemo.model.User;
import net.alibi.projectDemo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

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
    public User createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @PostMapping("/{id}")
    public User editUser(@PathVariable(value = "id") Long userId, @RequestBody User user) {
        User rpUser = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Error teacher with id "+ userId +" not found"));
        rpUser.setFirstName(user.getFirstName());
        rpUser.setLastName(user.getLastName());
        rpUser.setUserRoles(user.getUserRoles());
        rpUser.setStatus(user.getStatus());

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
