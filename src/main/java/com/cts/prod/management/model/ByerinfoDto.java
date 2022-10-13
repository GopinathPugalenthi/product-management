package com.cts.prod.management.model;

import lombok.Data;

@Data
public class ByerinfoDto{
    private int buyerId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String pin;
    private String phone;
    private String email;
    private int productId;
//    private String productName;//
    private int bidAmount;
    private int sellerId;//
}
