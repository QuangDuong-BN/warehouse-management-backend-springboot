package com.kma.warehouseManagement.entityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto1 {
    String name;
    String maSp;
    Integer soLuong;
    LocalDate date;
    String category;
    String storageId;
}
