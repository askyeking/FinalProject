package com.skilldistillery.swag.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.swag.entities.CommentFromVendor;

public interface CommentFromVendorRepository extends JpaRepository<CommentFromVendor, Integer> {

}
