package net.alibi.projectDemo.listener;

import lombok.RequiredArgsConstructor;
import net.alibi.projectDemo.model.FileDB;
import net.alibi.projectDemo.model.Status;
import net.alibi.projectDemo.repository.FileDBRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static net.alibi.projectDemo.ProjectDemoApplication.MY_QUEUE;

@RequiredArgsConstructor
@EnableRabbit
@Component
public class FileMessageListener {

    private final FileDBRepository fileDBRepository;

    @RabbitListener(queues = MY_QUEUE)
    public void receiveMessage(String massage) {
        FileDB fileDB = fileDBRepository.findById(massage).orElseThrow(RuntimeException::new);
        fileDB.setStatus(Status.ACTIVE);
        fileDBRepository.save(fileDB);
    }
}
