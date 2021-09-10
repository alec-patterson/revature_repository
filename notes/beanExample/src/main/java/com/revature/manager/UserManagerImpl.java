package com.revature.manager;

import com.revature.dao.UserDao;
import com.revature.model.User;

public class UserManagerImpl implements UserManager{
	
	private String role;
	private User user;
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// make sure you have a default constructor
	public UserManagerImpl() {}
	
	// can set a constructor argument within the bean tag with 
	// <constructor-arg type="datatype" value="nameofvar"></constructor-arg>
//	public UserManagerImpl(String role) {
//		this.role = role;
//	}
	
	public boolean login(String username, String password) {
		System.out.println("role in manager: " + this.role);
		System.out.println(this.user.getName());
		if(userDao.findByUsernameAndPassword(username, password) != null)
			return true;
		return false;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	// when using a property tag to set a variable you need to have a setter
	// in the class
//	public void setRole(String role) {
//		this.role = role;
//	}
	
	public void load() {
		System.out.println("Load called");
	}
	
	public void clear() {
		System.out.println("destroy called");
	}
}
