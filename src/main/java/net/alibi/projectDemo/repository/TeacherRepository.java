package net.alibi.projectDemo.repository;

import net.alibi.projectDemo.model.Teacher;
import net.alibi.projectDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<User> findByEmail(String email);
}
