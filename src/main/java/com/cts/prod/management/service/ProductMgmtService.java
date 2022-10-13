package com.cts.prod.management.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.prod.management.entity.Byerinfo;
import com.cts.prod.management.entity.Product;
import com.cts.prod.management.entity.Sellerinfo;
import com.cts.prod.management.model.ByerinfoDto;
import com.cts.prod.management.model.ProductDto;
import com.cts.prod.management.repository.ByerRepository;
import com.cts.prod.management.repository.ProductRepository;

@Service
public class ProductMgmtService {

	@Autowired
	ProductRepository repository;

	@Autowired
	ByerRepository byerRepository;

	public void addProduct(ProductDto productDto) throws Exception {
		Product prod = new Product();
		Sellerinfo sellerinfo = new Sellerinfo();
		try {
			String startDate = productDto.getBidEndDate();
			Date date = Date.valueOf(startDate);

			prod.setProductName(productDto.getProductName());
			String category = productDto.getCategory();
			if (category.equalsIgnoreCase("Painting") || category.equalsIgnoreCase("Sculptor")
					|| category.equalsIgnoreCase("Ornament")) {
				prod.setCategory(category);
			} else {
				throw new Exception("Check the Product Category");
			}
			prod.setShortDescription(productDto.getShortDescription());
			prod.setDetailedDescription(productDto.getDetailedDescription());
			prod.setStartingPrice(productDto.getStartingPrice());
			prod.setBidEndDate(date);

			sellerinfo.setSellerId(productDto.getSellerId());
			prod.setSellerinfo(sellerinfo);
			repository.save(prod);
		} catch (Exception e) {
			throw e;
		}
	}

	public void deleteByProductId(int productId) {
		repository.deleteByProductId(productId);

	}

//	public Product findAllByProductId(int productId) {
//		return repository.findAllByProductId(productId);
//	}

	public ProductDto findAllByProductId(int productId) {
		try {
			List<Byerinfo> byerinfoLst = byerRepository.findAllByProductId(productId);
			Product prod = repository.findAllByProductId(productId);

			List<ByerinfoDto> byerInfoDtoLst = new ArrayList<>();

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			ProductDto prodInfoDto = new ProductDto();
			prodInfoDto.setProductId(prod.getProductId());
			prodInfoDto.setProductName(prod.getProductName());
			prodInfoDto.setShortDescription(prod.getShortDescription());
			prodInfoDto.setDetailedDescription(prod.getDetailedDescription());
			prodInfoDto.setCategory(prod.getCategory());
			prodInfoDto.setStartingPrice(prod.getStartingPrice());
			prodInfoDto.setBidEndDate(formatter.format(prod.getBidEndDate()));

			for (Byerinfo byerinfo : byerinfoLst) {
				ByerinfoDto byerInfoDto = new ByerinfoDto();
				byerInfoDto.setBuyerId(byerinfo.getBuyerId());
				byerInfoDto.setFirstName(byerinfo.getFirstName());
				byerInfoDto.setLastName(byerinfo.getLastName());
				byerInfoDto.setAddress(byerinfo.getAddress());
				byerInfoDto.setCity(byerinfo.getCity());
				byerInfoDto.setState(byerinfo.getState());
				byerInfoDto.setPin(byerinfo.getPin());
				byerInfoDto.setPhone(byerinfo.getPhone());
				byerInfoDto.setEmail(byerinfo.getEmail());
				byerInfoDto.setBidAmount(byerinfo.getBidAmount());
				byerInfoDtoLst.add(byerInfoDto);
			}

			prodInfoDto.setByerInfoDto(byerInfoDtoLst);
			return prodInfoDto;
		} catch (Exception e) {
			throw e;
		}

	}

	public void saveBidValues(ByerinfoDto byerinfoDto) throws Exception {
		Byerinfo byerinfo = new Byerinfo();
		Sellerinfo sellerinfo = new Sellerinfo();
		Product prod = new Product();

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = new java.util.Date();
			formatter.format(date);

			prod = repository.findAllByProductId(byerinfoDto.getProductId());
//					repository.findBidEndDateByProductId(byerinfoDto.getProductId());

			if (date.before(prod.getBidEndDate())|| date.equals(prod.getBidEndDate())) {

				byerinfo.setFirstName(byerinfoDto.getFirstName());
				byerinfo.setLastName(byerinfoDto.getLastName());
				byerinfo.setAddress(byerinfoDto.getAddress());
				byerinfo.setCity(byerinfoDto.getCity());
				byerinfo.setState(byerinfoDto.getState());
				byerinfo.setPin(byerinfoDto.getPin());
				byerinfo.setPhone(byerinfoDto.getPhone());
				if (isValidEmailAddress(byerinfoDto.getEmail())) {
					byerinfo.setEmail(byerinfoDto.getEmail());
				} else {
					throw new Exception("Email Id not Valid");
				}
				byerinfo.setBidAmount(byerinfoDto.getBidAmount());

				prod.setProductId(byerinfoDto.getProductId());
				sellerinfo.setSellerId(byerinfoDto.getSellerId());
				prod.setSellerinfo(sellerinfo);

				byerinfo.setProduct(prod);
				byerRepository.save(byerinfo);
			} else {
				throw new Exception("Please check the Bid End Date");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void updateProduct(int productId, String buyerEmailld, int newBidAmount) {
		try {
			Byerinfo updateByerinfo = byerRepository.findAllByProductIdAndEmail(productId, buyerEmailld);
			updateByerinfo.setBidAmount(newBidAmount);
			byerRepository.save(updateByerinfo);
		} catch (Exception e) {
			throw e;
		}

	}

	public boolean isValidEmailAddress(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();

	}
}
