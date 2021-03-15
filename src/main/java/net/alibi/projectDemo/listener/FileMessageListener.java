package net.alibi.projectDemo.listener;

import lombok.Data;
import net.alibi.projectDemo.model.FileDB;
import net.alibi.projectDemo.repository.FileDBRepository;
import org.springframework.stereotype.Component;


@Data
@Component
public class FileMessageListener {

    private final FileDBRepository fileDBRepository;

    public void receiveMessage(FileDB message) {
        fileDBRepository.save(message);
    }
}
