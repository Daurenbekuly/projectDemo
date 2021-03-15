package net.alibi.projectDemo.controller;

import lombok.Data;
import net.alibi.projectDemo.model.Diary;
import net.alibi.projectDemo.model.Scholar;
import net.alibi.projectDemo.repository.DiaryRepository;
import net.alibi.projectDemo.repository.ScholarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/scholar")
public class ScholarController {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final DiaryRepository diaryRepository;
    private final ScholarRepository scholarRepository;

    @GetMapping
    public List<Scholar> getAllScholars() {
        return scholarRepository.findAll();
    }

    @GetMapping("/{id}")
    public Scholar getScholar(@PathVariable(value = "id") Long scholarId) {
        return scholarRepository.findById(scholarId).orElseThrow(
                () -> new RuntimeException("Error scholar with id "+ scholarId +" not found"));
    }

    @PostMapping
    public Scholar createScholar(@RequestBody Scholar scholar) {
        scholar.setPassword(passwordEncoder.encode(scholar.getPassword()));
        return scholarRepository.save(scholar);
    }

    @PostMapping("/{id}")
    public Scholar editScholar(@PathVariable(value = "id") Long scholarId, @RequestBody Scholar scholar) {
        Scholar rpScholar = scholarRepository.findById(scholarId).orElseThrow(
                () -> new RuntimeException("Error scholar with id "+ scholarId +" not found"));
        rpScholar.setFirstName(scholar.getFirstName());
        rpScholar.setLastName(scholar.getLastName());
        rpScholar.setRole(scholar.getRole());
        rpScholar.setStatus(scholar.getStatus());

        return scholarRepository.save(rpScholar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteScholar(@PathVariable (value = "id") Long scholarId) {
        Scholar scholar = scholarRepository.findById(scholarId).orElseThrow(
                () -> new RuntimeException("Error scholar with id "+ scholarId +" not found" ));
        scholarRepository.delete(scholar);
        return ResponseEntity.ok().build();

    }
}
