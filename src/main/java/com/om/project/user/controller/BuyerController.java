package com.om.project.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.om.project.user.dto.BuyerDTO;
import com.om.project.user.dto.CartDTO;
import com.om.project.user.dto.LoginDTO;
import com.om.project.user.dto.ProductDTO;
import com.om.project.user.dto.WishlistDTO;
import com.om.project.user.entity.Buyer;
import com.om.project.user.service.BuyerService;
import com.om.project.user.service.SellerService;

@RestController
public class BuyerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	Environment environment;
	

	@Autowired
	BuyerService buyerservice;
	@Autowired
	SellerService sellerservice;

	// Register buyer
	@PostMapping(value = "/buyer/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> generateBuyer(@Valid @RequestBody BuyerDTO buyerDTO) throws Exception {

		String successMessage = environment.getProperty("API.INSERT_SUCCESS");
		logger.info("Registration request for buyer with data {}", buyerDTO);
		buyerservice.saveBuyerDetails(buyerDTO);
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);

	}

	// Get all buyers
	@GetMapping(value = "/buyers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuyerDTO>> getAllBuyer() throws Exception {

		List<BuyerDTO> buyerDTOs = buyerservice.getAllBuyerDetails();
		return new ResponseEntity<>(buyerDTOs, HttpStatus.OK);

	}

	// Get buyer by id
	@GetMapping(value = "/buyer/{buyerid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BuyerDTO> getBuyerById(@PathVariable String buyerid) throws Exception {

		BuyerDTO buyer = buyerservice.getBuyerById(buyerid);
		return new ResponseEntity<>(buyer, HttpStatus.OK);

	}

	// Buyer login
	@PostMapping(value = "/buyer/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {

		buyerservice.buyerLogin(loginDTO);
		logger.info("Login request for buyer {} with password {}", loginDTO.getEmail(), loginDTO.getPassword());
		String successMessage = environment.getProperty("API.LOGIN_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);

	}

	// Delete buyer
   	//@DeleteMapping(value = "/buyer/{buyerid}")
	//public ResponseEntity<String> removeBuyer(@PathVariable String buyerid) throws Exception {
	//	buyerservice.removeBuyer(buyerid);
	//	String successMessage = environment.getProperty("API.REMOVE_SUCCESS");
	//	return new ResponseEntity<>(successMessage, HttpStatus.OK);
	//}

   // Delete product from wishlist
	//@DeleteMapping(value = "/wishlist/{buyerid}")
	//public ResponseEntity<String> removwWishlist(@PathVariable String buyerid) throws Exception {
	//	buyerservice.removeWishlist(buyerid);
	//	String successMessage = environment.getProperty("API.REMOVE_SUCCESS");
	//	return new ResponseEntity<>(successMessage, HttpStatus.OK);
	//}

	
	// Delete product from cart
	//@DeleteMapping(value = "/cart/{buyerid}")
	//public ResponseEntity<String> removeCart(@PathVariable String buyerid) throws Exception {
        //	buyerservice.removeCart(buyerid);
	//	String successMessage = environment.getProperty("API.REMOVE_SUCCESS");
	//	return new ResponseEntity<>(successMessage, HttpStatus.OK);
      //}

	// Update is privileged
	@RequestMapping(value = "/isprivilege/{buyerid}", method = RequestMethod.PUT)
	public ResponseEntity<Buyer> updateIsprivilege(@RequestBody Buyer buyer, @PathVariable String buyerid)
			throws Exception {
		Buyer buyers = buyerservice.updateIsprivilege(buyer, buyerid);
		return new ResponseEntity<>(buyers, HttpStatus.OK);

	}

}
