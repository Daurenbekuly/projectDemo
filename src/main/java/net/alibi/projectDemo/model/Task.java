package net.alibi.projectDemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.alibi.projectDemo.model.enums.Level;
import net.alibi.projectDemo.model.enums.Subject;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false, exclude = {"user"})
@Entity
@Table(name = "c_task")
public class Task extends BaseModel {

    @Enumerated(value = EnumType.STRING)
    @Column(name = "level_")
    private Level level;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "subject_")
    private Subject subject;

    @Column(name = "question_")
    private String question;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_")
    private User user;
}
