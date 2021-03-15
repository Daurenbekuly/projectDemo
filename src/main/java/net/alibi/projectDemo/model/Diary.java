package net.alibi.projectDemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "c_diary")
public class Diary extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scholar_id_")
    private Scholar scholar;

    @Column(name = "rating")
    private String rating;
}