package com.cookingrecipe.service.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cookingrecipe.dao.user.UserDao;
import com.cookingrecipe.entity.Role;
import com.cookingrecipe.entity.User;
import com.cookingrecipe.model.user.CustomeUserDetailsModel;
import com.cookingrecipe.model.user.UserRequest;

@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {
	@Autowired
	UserDao userDao;

	@Transactional(readOnly = false)
	public void createAccount(UserRequest p) {
		userDao.createAccount(p.convertUser());
	}

	public User getById(Integer id) {
		return userDao.getById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		System.out.println("In loadUserByUsername -UserService");
		User user = userDao.findUserByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		UserRequest userRequest = copyUser(user);
		return new CustomeUserDetailsModel(userRequest);
	}

	private UserRequest copyUser(User user) {
		UserRequest userRequest = new UserRequest();
		userRequest.setEmail(user.getMail());
		userRequest.setPassword(user.getPassword());
		userRequest.setRole("ROLE_ADMIN");
//		userRequest.setRole(Role.intToRole((user.getRole())));
		return userRequest;
	}
	
	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUserTokens(String username) {
		// TODO Auto-generated method stub
		
	}
}
