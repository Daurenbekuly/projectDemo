package net.alibi.projectDemo.repository;

import net.alibi.projectDemo.model.Scholar;
import net.alibi.projectDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScholarRepository extends JpaRepository<Scholar, Long> {
    Optional<User> findByEmail(String email);
}
