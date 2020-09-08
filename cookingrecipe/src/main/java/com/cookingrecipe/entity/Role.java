package com.cookingrecipe.entity;

public enum Role {
	ADMIN(0), USER(1);
	
	public final int value;
	public static final int ROLE_ADMIN = ADMIN.value;
	public static final int ROLE_USER = USER.value;
	
	Role(int role) {
		// TODO Auto-generated constructor stub
		value = role;
	}
	
	public static String getRoleByInt(int r) {
		Role role = intToRole(r);
		return role.name();
	}
	
	public static Role intToRole(int i) {
		if (i == ROLE_ADMIN) {
			return ADMIN;
		}
		return USER;
	}
	
	public static int roleToInt(Role r) {
		if (r.equals(ADMIN)) {
			return ROLE_ADMIN;
		} 
		return ROLE_USER;
	}
}
