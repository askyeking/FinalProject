package com.skilldistillery.swag.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.swag.entities.CommentFromCustomer;

public interface CommentFromCustomerRepository extends JpaRepository<CommentFromCustomer, Integer> {

}
