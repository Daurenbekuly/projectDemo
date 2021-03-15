package net.alibi.projectDemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class User extends BaseModel {

    @Column(name = "email_", unique = true)
    private String email;

    @Column(name = "password_")
    private String password;

    @Column(name = "first_name_")
    private String firstName;

    @Column(name = "last_name_")
    private String lastName;

    @Column(name = "phone_")
    private Integer phone;

    @Column(name = "age_")
    private Integer age;

    @Column(name = "gender_")
    private String gender;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role_")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_")
    private Status status;

}
