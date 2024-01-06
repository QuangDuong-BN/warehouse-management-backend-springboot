package com.kma.warehouseManagement.entityDto;

import com.kma.warehouseManagement.enumCustom.TypeProduct;

public class ProductCountResultDt0 {

    public TypeProduct type;
    public Long soLuong;

    public ProductCountResultDt0(TypeProduct type, Long soLuong) {
        this.type = type;
        this.soLuong = soLuong;
    }
}
