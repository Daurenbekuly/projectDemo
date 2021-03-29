package net.alibi.projectDemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.alibi.projectDemo.model.enums.Role;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "c_user_role")
public class UserRole extends BaseModel {

    @Enumerated(EnumType.STRING)
    @Column(name = "role_")
    private Role role;
}
