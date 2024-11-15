package com.sousa.demo_parking_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sousa.demo_parking_api.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false,unique = true , length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.ROLE_CLIENTE;

    @Column(name = "date_creation")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dateCreation = LocalDateTime.now();

    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_by")
    private String updateBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }


}
