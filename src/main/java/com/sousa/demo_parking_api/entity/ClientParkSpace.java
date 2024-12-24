package com.sousa.demo_parking_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "plient_parkSpace")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ClientParkSpace {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15, unique = true)
    private String receipt;

    @Column(nullable = false, length = 8, unique = true)
    @Pattern(regexp = "[A-Z]{3}-[0-9]{4}")
    private String plate;

    @Column(nullable = false, length = 45)
    private String mark;

    @Column(nullable = false, length = 45)
    private String color;

    @Column(columnDefinition = "decimal(7,2)")
    private BigDecimal value;

    @Column(columnDefinition = "decimal(7,2)")
    private BigDecimal discount;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(name = "input_date")
    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime inputDate;

    @Column(name = "input_date")
    private LocalDateTime exitDate;

    @Column(name = "date_creation")
    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dateCreate;


    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    @CreatedBy
    @Column(name = "create_by")
    private String createBy;

    @LastModifiedBy
    @Column(name = "update_by")
    private String updateBy;


}
