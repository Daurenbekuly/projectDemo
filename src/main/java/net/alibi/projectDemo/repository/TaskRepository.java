package net.alibi.projectDemo.repository;

import net.alibi.projectDemo.model.Task;
import net.alibi.projectDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    void deleteByUser(User user);
}
