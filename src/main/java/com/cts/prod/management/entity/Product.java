package com.cts.prod.management.entity;

import java.io.Serializable;
import java.sql.Date;
//import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
@Entity(name="product")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="product_id", unique=true, nullable=false, precision=10)
    private int productId;
    @Column(name="product_name", nullable=false, length=30)
    private String productName;
    @Column(name="short_description", length=255)
    private String shortDescription;
    @Column(name="detailed_description", length=255)
    private String detailedDescription;
    @Column(name="category", length=10)
    private String category;
    @Column(name="starting_price", precision=10)
    private int startingPrice;
    @Column(name="bid_end_date")
    private Date bidEndDate;
    @OneToMany(mappedBy="product")
    @JsonIgnore
    private Set<Byerinfo> byerinfo;
    @ManyToOne(cascade = CascadeType.MERGE, optional=false)
    @JoinColumn(name="seller_id")
    @JsonIgnore
    private Sellerinfo sellerinfo;
}
