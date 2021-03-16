package net.alibi.projectDemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false, exclude = "scholars")
@Entity
@Table(name = "c_teacher")
public class Teacher extends User {

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "c_users",
            joinColumns = @JoinColumn(name = "teacher_id_"),
            inverseJoinColumns = @JoinColumn(name = "scholar_id_")
    )
    Set<Scholar> scholars = new HashSet<>();
}
