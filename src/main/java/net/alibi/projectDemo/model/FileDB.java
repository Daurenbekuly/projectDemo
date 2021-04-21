package net.alibi.projectDemo.model;

import lombok.*;
import net.alibi.projectDemo.model.enums.Status;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "task")
@Entity
@Table(name = "c_file")
public class FileDB {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id_")
    private Task task;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_", columnDefinition = "varchar(255) default 'BANNED'")
    private Status status;

    public FileDB(String name, String type, byte[] data,Task task) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.task = task;
    }
}
