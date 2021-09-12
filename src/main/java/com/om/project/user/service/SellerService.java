package com.om.project.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.om.project.user.dto.LoginDTO;
import com.om.project.user.dto.SellerDTO;
import com.om.project.user.entity.Seller;
import com.om.project.user.exception.InfyMarketException;
import com.om.project.user.repository.SellerRepository;

@Service
@Transactional
public class SellerService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SellerRepository sellerrepo;

	// Seller register
	public void saveSellerDetails(SellerDTO sellerDTO) throws InfyMarketException {
		logger.info("Registration request for seller with data {}", sellerDTO);
		Seller seller = sellerDTO.generateSeller();
		sellerrepo.save(seller);
	}

	// Get all sellers
	public List<SellerDTO> getAllSellerDetails() throws InfyMarketException {

		Iterable<Seller> sellers = sellerrepo.findAll();
		List<SellerDTO> sellerDTOs = new ArrayList<>();

		sellers.forEach(seller -> {
			SellerDTO sellerDTO = SellerDTO.valueOf(seller);
			sellerDTOs.add(sellerDTO);
		});
		if (sellerDTOs.isEmpty())
			throw new InfyMarketException("Service.SELLERS_NOT_FOUND");
		logger.info("Seller Details : {}", sellerDTOs);
		return sellerDTOs;
	}

	// Seller Login
	public boolean sellerLogin(LoginDTO loginDTO) throws InfyMarketException {
		logger.info("Login request for selelr {} with password {}", loginDTO.getEmail(), loginDTO.getPassword());
		Seller sell = sellerrepo.findByEmail(loginDTO.getEmail());
		if (sell != null && sell.getPassword().equals(loginDTO.getPassword())) {
			return true;
		} else {
			throw new InfyMarketException("Service.DETAILS_NOT_FOUND");
		}
	}

	// Delete Seller
	//public void removeSeller(String sellerid) throws InfyMarketException {
	//	Optional<Seller> seller = sellerrepo.findById(sellerid);
	//	seller.orElseThrow(() -> new InfyMarketException("Service.SELLERS_NOT_FOUND"));
	//	sellerrepo.deleteById(sellerid);
	//}
}
