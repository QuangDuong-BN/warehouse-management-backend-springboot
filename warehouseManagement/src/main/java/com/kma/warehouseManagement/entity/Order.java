package com.kma.warehouseManagement.entity;

import com.kma.warehouseManagement.enumCustom.StatusOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer ownerId;
    private Integer itemId;
    @Enumerated(EnumType.STRING)
    private StatusOrder status;
    // Đổi tên cột 'option' thành 'order_option'
    @Column(name = "order_option")
    private String option;
    private String message;
    private Integer storageId;
    private Integer marketId;

}
