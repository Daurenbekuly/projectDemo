package net.alibi.projectDemo.repository;

import net.alibi.projectDemo.model.FileDB;
import net.alibi.projectDemo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileDBRepository extends JpaRepository<FileDB, String> {
    List<FileDB> findByTask(Task task);
}
