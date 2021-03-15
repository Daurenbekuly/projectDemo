package net.alibi.projectDemo.repository;

import net.alibi.projectDemo.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDBRepository extends JpaRepository<FileDB, String> {
}
