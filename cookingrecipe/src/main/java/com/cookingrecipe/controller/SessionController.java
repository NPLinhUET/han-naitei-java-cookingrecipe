package com.cookingrecipe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cookingrecipe.model.user.UserRequest;
import com.cookingrecipe.service.user.IUserService;

@Controller
public class SessionController {
	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	@GetMapping(value = "/login")
	public String loginForm(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {
		if (error != null) {
			model.addAttribute("error", "Invalid username and password!");
			return "loginSuccess";
		}

		if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}
		return "login";
	}

//	
//	@PostMapping(value = "/users")
//	public String create(@ModelAttribute("user") @Validated UserRequest userModel, BindingResult bindingResult,
//			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
//		if (bindingResult.hasErrors()) {
//			System.out.println("Returning register.jsp page, validate failed");
//			return "signup";
//		}
//		UserRequest user = userService.addUser(userModel);
//		// Add message to flash scope
////		flash.success("user.create.success");
////		flash.keep();
//		request.login(userModel.getEmail(), userModel.getPassword());
//		return "loginSuccess";
//	}
	@GetMapping("/loginSuccess")
	public String loginSuccess() {
		return "loginSuccess";
	}
}
