package net.alibi.projectDemo.services;

import lombok.RequiredArgsConstructor;
import net.alibi.projectDemo.model.FileDB;
import net.alibi.projectDemo.model.Task;
import net.alibi.projectDemo.repository.FileDBRepository;
import net.alibi.projectDemo.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

//import static net.alibi.projectDemo.queue.RabbitConfiguration.MY_QUEUE;

@RequiredArgsConstructor
@Service
public class FileStorageService {

//    private final RabbitTemplate rabbitTemplate;
    private final FileDBRepository fileDBRepository;
    private final TaskRepository taskRepository;


    public void store(MultipartFile file, Long taskId) throws IOException {
        Task task = taskRepository.findById(taskId).orElseThrow(RuntimeException::new);
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), task);
        fileDBRepository.save(fileDB);
//        rabbitTemplate.convertAndSend(MY_QUEUE, fileDB.getId());
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Stream<FileDB> getAllFiles(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        return fileDBRepository.findByTask(task).stream();
    }
}
