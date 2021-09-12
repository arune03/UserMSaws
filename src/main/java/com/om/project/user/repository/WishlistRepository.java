package com.om.project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.om.project.user.entity.Wishlist;
@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, String> {

    
}
