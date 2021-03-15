package net.alibi.projectDemo.repository;

import net.alibi.projectDemo.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
