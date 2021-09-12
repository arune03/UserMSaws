package com.om.project.user.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.om.project.user.dto.LoginDTO;
import com.om.project.user.dto.ProductDTO;
import com.om.project.user.dto.SellerDTO;
import com.om.project.user.service.SellerService;

@RestController

public class SellerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	Environment environment;

	@Autowired
	SellerService sellerservice;

	// Seller register
	@PostMapping(value = "/seller/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createSellerDetails(@Valid @RequestBody SellerDTO sellerDTO) throws Exception {

		logger.info("Registration request for seller with data {}", sellerDTO);
		sellerservice.saveSellerDetails(sellerDTO);
		String successMessage = environment.getProperty("API.INSERT_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);

	}

	// Get all sellers
	@GetMapping(value = "/sellers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SellerDTO>> getAllSellerDeatils() throws Exception {

		List<SellerDTO> sellerDTOs = sellerservice.getAllSellerDetails();
		return new ResponseEntity<>(sellerDTOs, HttpStatus.OK);

	}

	// Seller login
	@PostMapping(value = "/seller/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> Login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {

		sellerservice.sellerLogin(loginDTO);
		logger.info("Login request for seller {} with password {}", loginDTO.getEmail(), loginDTO.getPassword());
		String successMessage = environment.getProperty("API.LOGIN_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);

	}

	// Delete seller
	//@DeleteMapping(value = "/seller/{sellerid}")
	//public ResponseEntity<String> removeSeller(@PathVariable String sellerid) throws Exception {
       //	sellerservice.removeSeller(sellerid);
	//	String successMessage = environment.getProperty("API.REMOVE_SUCCESS");
	//	return new ResponseEntity<>(successMessage, HttpStatus.OK);
	//}

	
}
