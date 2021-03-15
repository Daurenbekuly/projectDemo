package net.alibi.projectDemo.controller;

import lombok.Data;
import net.alibi.projectDemo.model.Diary;
import net.alibi.projectDemo.repository.DiaryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/scholar/diary")
public class DiaryController {

    private final ModelMapper modelMapper;
    private final DiaryRepository diaryRepository;

    @GetMapping
    public List<Diary> getAllDiary() {
        return diaryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Diary getDiary(@PathVariable(value = "id") Long diaryId) {
        return diaryRepository.findById(diaryId).orElseThrow(
                () -> new RuntimeException("Error diary with id "+ diaryId +" not found"));
    }


    @PostMapping
    public Diary createDiary(@RequestBody Diary diary) {
        return diaryRepository.save(diary);
    }

    @PostMapping("/{id}")
    public Diary editDiary(@PathVariable(value = "id") Long diaryId, @RequestBody Diary diary) {
        Diary rpDiary = diaryRepository.findById(diaryId).orElseThrow(
                () -> new RuntimeException("Error diary with id "+ diaryId +" not found"));
        rpDiary.setScholar(diary.getScholar());

        return diaryRepository.save(rpDiary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteDiary(@PathVariable (value = "id") Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(
                () -> new RuntimeException("Error diary with id "+ diaryId +" not found" ));
        diaryRepository.delete(diary);
        return ResponseEntity.ok().build();

    }
}
