package com.tyagi.springsecurity6.model;

import com.tyagi.springsecurity6.enums.OriginType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private UUID serialNumber;

    private Double price;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private OriginType origin; // handmade or factory made

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedDate;
}
