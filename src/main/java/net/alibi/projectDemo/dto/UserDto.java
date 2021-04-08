package net.alibi.projectDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.alibi.projectDemo.model.enums.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private Integer phone;
    private Integer age;
    private String email;
    private String firstName;
    private String gender;
    private String lastName;
    private String userName;
    private String password;
    private Status status;
    private String role;
}
