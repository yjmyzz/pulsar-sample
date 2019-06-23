package com.cnlbogs.yjmyzz.pulsarsample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jimmy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品价格
     */
    private BigDecimal price;

    /**
     * 产品Id
     */
    private Long productId;

    /**
     * 出厂日期
     */
    private Date dateOfProduction;
}
