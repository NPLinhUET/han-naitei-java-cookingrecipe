package com.cookingrecipe.model.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.cookingrecipe.entity.Role;

public class CustomeUserDetailsModel extends org.springframework.security.core.userdetails.User{
	private UserRequest user = null;

	public CustomeUserDetailsModel(UserRequest user) {
		super(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
		this.user = user;
	}

	public UserRequest getUser() {
		return user;
	}

	public void setUser(UserRequest user) {
		this.user = user;
	}

	private static Collection<? extends GrantedAuthority> mapRolesToAuthorities(String r) {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		authorities.add(new SimpleGrantedAuthority(Role.getRoleByInt(Role.roleToInt(r))));
		return authorities;
	}

}
