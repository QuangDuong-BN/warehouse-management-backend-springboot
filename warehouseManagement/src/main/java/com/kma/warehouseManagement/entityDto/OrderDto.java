package com.kma.warehouseManagement.entityDto;

import com.kma.warehouseManagement.enumCustom.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    Integer id;
    Integer ownerId;
    List<ProductDto2> lineItems;
    StatusOrder status;
    String option;
    String message;
    Integer storageId;
    Integer marketId;
}
