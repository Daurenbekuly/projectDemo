package net.alibi.projectDemo.controller;

import lombok.RequiredArgsConstructor;
import net.alibi.projectDemo.dto.TaskDto;
import net.alibi.projectDemo.model.Task;
import net.alibi.projectDemo.model.User;
import net.alibi.projectDemo.repository.TaskRepository;
import net.alibi.projectDemo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @GetMapping
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable(value = "id") Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(
                () -> new RuntimeException("Error task with id "+ taskId +" not found"));
    }

    @PostMapping("/{username}")
    public Task createTask(@PathVariable(value = "username") String username, @Valid @RequestBody TaskDto taskDto) {
        User user = userRepository.findByUserName(username).orElseThrow(() ->
                new RuntimeException("Error user with name "+ username +" not found"));

        Task task = modelMapper.map(taskDto, Task.class);
        task.setUser(user);
        return taskRepository.save(task);
    }

    @PostMapping("/{id}/edit")
    public Task editTask(@PathVariable(value = "id") Long taskId, @Valid @RequestBody TaskDto taskDto) {
        Task rqTask = modelMapper.map(taskDto, Task.class);
        Task rpTask = taskRepository.findById(taskId).orElseThrow(
                () -> new RuntimeException("Error task with id "+ taskId +" not found"));
        rpTask.setLevel(rqTask.getLevel());
        rpTask.setSubject(rqTask.getSubject());
        rpTask.setQuestion(rqTask.getQuestion());

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
