package net.alibi.projectDemo.controller;

import lombok.Data;
import net.alibi.projectDemo.model.Task;
import net.alibi.projectDemo.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@Data
@RestController
@RequestMapping("/api/teacher/task")
public class TaskController implements Serializable {

    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable(value = "id") Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(
                () -> new RuntimeException("Error task with id "+ taskId +" not found"));
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PostMapping("/{id}")
    public Task editTask(@PathVariable(value = "id") Long taskId, @RequestBody Task task) {
        Task rpTask = taskRepository.findById(taskId).orElseThrow(
                () -> new RuntimeException("Error task with id "+ taskId +" not found"));
        rpTask.setLevel(task.getLevel());
        rpTask.setQuestion(task.getQuestion());
        rpTask.setTheme(task.getTheme());
        rpTask.setTeacher(task.getTeacher());

        return taskRepository.save(rpTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable (value = "id") Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new RuntimeException("Error task with id "+ taskId +" not found" ));
        taskRepository.delete(task);
        return ResponseEntity.ok().build();

    }
}
