package net.alibi.projectDemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.alibi.projectDemo.model.enums.Level;
import net.alibi.projectDemo.model.enums.Subject;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false, exclude = {"scholar"})
@Entity
@Table(name = "c_diary")
public class Diary extends BaseModel {

    @Enumerated(value = EnumType.STRING)
    @Column(name = "level_")
    private Level level;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "subject_")
    private Subject subject;

    @Column(name = "grade_")
    private Integer grade;

    @Column(name = "rating_")
    private Integer rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scholar_id_")
    private User scholar;
}
