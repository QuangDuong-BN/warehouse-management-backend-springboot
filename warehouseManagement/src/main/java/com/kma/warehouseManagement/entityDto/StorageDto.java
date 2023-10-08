package com.kma.warehouseManagement.entityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorageDto {
    String name;
    String latitude;
    String longtitude;
    Integer marketId;
}
