package net.alibi.projectDemo.repository;

import net.alibi.projectDemo.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDBRepository extends JpaRepository<FileDB, String> {
}
