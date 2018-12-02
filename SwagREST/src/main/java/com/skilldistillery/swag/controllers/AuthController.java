package com.skilldistillery.swag.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.services.AuthService;

@RestController
@CrossOrigin({"*", "http://localhost:4207"})
public class AuthController {
	
	@Autowired
	AuthService authSvc;
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public User register(@RequestBody User user, HttpServletResponse res) {
	  
	  if (user == null) {
	    res.setStatus(400);
	    return user;
	  }
	  
	  user =  authSvc.register(user);

	  return user;
	}

	@RequestMapping(path = "/authenticate", method = RequestMethod.GET)
	public Principal authenticate(Principal principal) {
	  return principal;
	}
	
	
	
	
	

}
