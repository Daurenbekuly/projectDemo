package net.alibi.projectDemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "c_task")
public class Task extends BaseModel {

    @Column(name = "level_")
    private String level;

    @Column(name = "theme_")
    private String theme;

    @Column(name = "question_")
    private String question;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id_")
    private Teacher teacher;
}
