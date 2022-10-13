package com.cts.prod.management.model;

import lombok.Data;

@Data
public class SellerinfoDto {

    private int sellerId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String pin;
    private String phone;
    private String email;
}
