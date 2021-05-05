package net.alibi.projectDemo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.alibi.projectDemo.model.enums.Level;
import net.alibi.projectDemo.model.enums.Subject;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class DiaryDto implements Serializable {
    private Subject subject;
    private Level level;
    private Integer grade;
}
