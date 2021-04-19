package net.alibi.projectDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.alibi.projectDemo.model.enums.Level;
import net.alibi.projectDemo.model.enums.Subject;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto implements Serializable {
    private Long id;
    private Level level;
    private String name;
    private String question;
    private Subject subject;
}
