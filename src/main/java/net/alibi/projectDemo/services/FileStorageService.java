package net.alibi.projectDemo.services;

import lombok.Data;
import net.alibi.projectDemo.ProjectDemoApplication;
import net.alibi.projectDemo.model.FileDB;
import net.alibi.projectDemo.repository.FileDBRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

@Data
@Service
public class FileStorageService {

    private final RabbitTemplate rabbitTemplate;
    private final FileDBRepository fileDBRepository;

    public void store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        rabbitTemplate.convertAndSend(ProjectDemoApplication.SFG_MESSAGE_QUEUE, fileDB);
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
