package com.cookingrecipe.dao.user;

import com.cookingrecipe.dao.interfaces.IGenericDAO;
import com.cookingrecipe.entity.User;

public interface UserDao extends IGenericDAO<User, Integer>{
	public void createAccount(User p);
	public User getById(Integer id);
	User findUserByEmail(String email);
}
