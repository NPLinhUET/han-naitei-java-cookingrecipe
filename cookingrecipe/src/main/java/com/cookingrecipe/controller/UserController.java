package com.cookingrecipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cookingrecipe.model.user.UserRequest;
import com.cookingrecipe.service.user.IUserService;
import com.cookingrecipe.service.user.UserService;

import com.sun.istack.logging.Logger;

@Controller
public class UserController {
	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	@RequestMapping(value = "/signup")
	public String index(Model model) {
		model.addAttribute("newuser", new UserRequest());
		return "signup";
	}

	@PostMapping(value = "/users")
	public String create(@ModelAttribute("newuser") UserRequest p, Model model) {
		this.userService.createAccount(p);
		return "redirect:/signup";
	}

	@RequestMapping(value = "/users/{userId}")
	public String profile(Model model) {
		return "profile";
	}
	

}
