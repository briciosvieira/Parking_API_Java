package com.sousa.demo_parking_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sousa.demo_parking_api.enums.StatusSpace;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Table(name = "ParkSpace")
@EntityListeners(AuditingEntityListener.class)
public class ParkSpace implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 4)
    private String code;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusSpace statusSpace = StatusSpace.LIVRE;

    @Column(name = "date_create")
    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dateCreate;

    @LastModifiedDate
    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    @CreatedBy
    @Column(name = "create_by")
    private String createBy;

    @LastModifiedBy
    @Column(name = "update_by")
    private String updateBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkSpace parkSpace = (ParkSpace) o;
        return Objects.equals(id, parkSpace.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
