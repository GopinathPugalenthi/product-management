package com.cts.prod.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.prod.management.model.AuditDetail;
import com.cts.prod.management.model.ByerinfoDto;
import com.cts.prod.management.model.ProductDto;
import com.cts.prod.management.repository.ProductRepository;
import com.cts.prod.management.service.ProductMgmtService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/e-auction/api/v1")
public class ProductMgmtController {
	
	@Autowired
	ProductRepository repository;
	
	@Autowired
	ProductMgmtService service;
	
	@Autowired
	FeignClientInterface feignClientInterface;
	
//	@GetMapping("/seller/show-bids/{productId}")
//	public ResponseEntity<Product> getAllBidsByProductId (@PathVariable String productId) {		
//		return new ResponseEntity<Product>(service.findAllByProductId(Integer.parseInt(productId)),  HttpStatus.OK);
//	}
	
	@GetMapping("/seller/show-bids/{productId}")
	public ResponseEntity<ProductDto> getAllBidsByProductId (@PathVariable String productId) {
		return new ResponseEntity<ProductDto>(service.findAllByProductId(Integer.parseInt(productId)),  HttpStatus.OK);
	}
	
	
	@PostMapping("/seller/add-product")
	public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto) throws Exception {
		AuditDetail ad = new AuditDetail();
		ad.setAction("Add Product");
		ad.setAuctionEntity("Product");
		ad.setAuditMessage("Product "+productDto.getProductName()+" has added successfully");
	    service.addProduct(productDto);
	    feignClientInterface.create(ad);
		return new ResponseEntity<>("Product Added Successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/seller/delete/{productId}")
	public ResponseEntity<String> deleteProductByProductId(@PathVariable String productId) {
		AuditDetail ad = new AuditDetail();
		ad.setAction("Delete Product");
		ad.setAuctionEntity("Product");
		ad.setAuditMessage("Product ID "+productId+" has deleted successfully");
	    service.deleteByProductId(Integer.parseInt(productId));
	    feignClientInterface.create(ad);
	    return new ResponseEntity<>("Product deleted Successfully for Product Id::"+productId, HttpStatus.OK);
	}
	
	@PostMapping("/buyer/place-bid")
	public ResponseEntity<String> placeBid(@RequestBody ByerinfoDto byerinfoDto) throws Exception {
		AuditDetail ad = new AuditDetail();
		ad.setAction("Place Bid");
		ad.setAuctionEntity("Byerinfo");
		ad.setAuditMessage("For Product ID "+byerinfoDto.getProductId()+"bid has added successfully");
		service.saveBidValues(byerinfoDto);
		feignClientInterface.create(ad);
		return new ResponseEntity<>("Bid Amount Placed Successfully", HttpStatus.OK);
	}
	
	@PutMapping("/buyer/update-bid/{productId}/{buyerEmailld}/{newBidAmount}")
	public ResponseEntity<String> updateProduct(@PathVariable int productId, @PathVariable String buyerEmailld, @PathVariable int newBidAmount) {
		
		AuditDetail ad = new AuditDetail();
		ad.setAction("Update Product Bid");
		ad.setAuctionEntity("Byerinfo");
		ad.setAuditMessage("For Product ID "+productId+"bid has added successfully");
		service.updateProduct(productId,buyerEmailld,newBidAmount);
		feignClientInterface.create(ad);
		return new ResponseEntity<>("Bid Amount updated Successfully", HttpStatus.OK);
	}
	
	
//	@PostMapping("/auditDetails")
//    public  ResponseEntity<String> create(@RequestBody AuditDetail auditDetail) {
//        feignClientInterface.create(auditDetail);
//        return new ResponseEntity<>("Details got auditted Successfully", HttpStatus.OK);
//    }
}