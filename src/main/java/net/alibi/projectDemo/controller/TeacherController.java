package net.alibi.projectDemo.controller;

import lombok.Data;
import net.alibi.projectDemo.model.Teacher;
import net.alibi.projectDemo.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable(value = "id") Long teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(
                () -> new RuntimeException("Error teacher with id "+ teacherId +" not found"));
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        return teacherRepository.save(teacher);
    }

    @PostMapping("/{id}")
    public Teacher editTeacher(@PathVariable(value = "id") Long teacherId, @RequestBody Teacher teacher) {
        Teacher rpTeacher = teacherRepository.findById(teacherId).orElseThrow(
                () -> new RuntimeException("Error teacher with id "+ teacherId +" not found"));
        rpTeacher.setFirstName(teacher.getFirstName());
        rpTeacher.setLastName(teacher.getLastName());
        rpTeacher.setRole(teacher.getRole());
        rpTeacher.setStatus(teacher.getStatus());
        rpTeacher.setScholars(teacher.getScholars());

        return teacherRepository.save(rpTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTeacher(@PathVariable (value = "id") Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(
                () -> new RuntimeException("Error teacher with id "+ teacherId +" not found" ));
        teacherRepository.delete(teacher);
        return ResponseEntity.ok().build();

    }
}
