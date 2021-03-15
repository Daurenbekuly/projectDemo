package net.alibi.projectDemo.dto;

import lombok.Data;
import net.alibi.projectDemo.model.Role;
import net.alibi.projectDemo.model.Status;

import java.io.Serializable;


@Data
public class UserDto implements Serializable {
    private String email;
    private String password;
}
