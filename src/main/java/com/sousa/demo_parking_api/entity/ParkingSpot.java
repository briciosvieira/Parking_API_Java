package com.sousa.demo_parking_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ParkingSpot {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 8, unique = true)
    private String plate;

    @Column(nullable = false, length = 45)
    private String mark;

    @Column(nullable = false, length = 45)
    private String model;

    @Column(nullable = false, length = 45)
    private String color;

    @Column(columnDefinition = "decimal(7,2)")
    private BigDecimal value;

    @Column(columnDefinition = "decimal(7,2)")
    private BigDecimal discount;

    @Column(nullable = false, length = 15, unique = true)
    private String receipt;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_vacancy",nullable = false)
    private Vacancy vacancy;

    @Column(name = "input_date")
    @CreatedDate
    private LocalDateTime inputDate;

    @Column(name = "exit_date")
    private LocalDateTime exitDate;

    @Column(name = "date_creation")
    @CreatedDate
    private LocalDateTime dateCreate;

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
        ParkingSpot that = (ParkingSpot) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
