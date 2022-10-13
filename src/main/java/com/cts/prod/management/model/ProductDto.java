package com.cts.prod.management.model;

import java.util.List;

import lombok.Data;

@Data
public class ProductDto{
    private int productId;
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private String category;
    private int startingPrice;
    private String bidEndDate;
    private int sellerId;
    private List<ByerinfoDto> byerInfoDto;
}
