package com.cookingrecipe.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.cookingrecipe.model.user.UserRequest;

public interface IUserService extends UserDetailsService, PersistentTokenRepository {
	void createAccount(UserRequest p);
}
