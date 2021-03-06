package net.alibi.projectDemo.repository;

import net.alibi.projectDemo.model.Diary;
import net.alibi.projectDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    @Query("FROM Diary WHERE id = ?1 AND level = ?2 AND subject = ?3")
    List<Diary> findByIdAndLevelAndSubject(Long id, String level, String subject);

    List<Diary> findByScholar(User user);

    void deleteByScholar(User user);

}
