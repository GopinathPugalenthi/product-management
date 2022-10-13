package com.cts.prod.management.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name="byerinfo")
public class Byerinfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="buyer_id", unique=true, nullable=false, precision=10)
    private int buyerId;
    @Column(name="first_name", nullable=false, length=30)
    private String firstName;
    @Column(name="last_name", nullable=false, length=25)
    private String lastName;
    @Column(name="address", length=255)
    private String address;
    @Column(name="city", length=30)
    private String city;
    @Column(name="state", length=30)
    private String state;
    @Column(name="pin", length=10)
    private String pin;
    @Column(name="phone", nullable=false, length=10)
    private String phone;
    @Column(name="email", nullable=false, length=50)
    private String email;
    @Column(name="bid_amount", precision=10)
    private int bidAmount;
    @ManyToOne(cascade = CascadeType.MERGE, optional=false)
    @JoinColumn(name="product_id")
    @JsonIgnore
    private Product product;
}
