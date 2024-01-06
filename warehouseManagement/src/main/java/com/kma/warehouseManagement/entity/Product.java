package com.kma.warehouseManagement.entity;

import com.kma.warehouseManagement.enumCustom.TypeProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String maSp;
    private Integer soLuong;
    private LocalDate date;
    private String category;
    @Enumerated(EnumType.STRING)
    private TypeProduct type;
    private Integer storageId;
}
