package com.revature.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.manager.AccountManager;

public class Main {

	public static void main(String[] args) {
//		UserManager mgr = new UserManagerImpl();
		
		//try-with-resources - AutoClosable
		
		try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")){
//		UserManager um = (UserManager) context.getBean("userMgr");
//		System.out.println(um.login("ajp", "test"));
		
		AccountManager accMgr = (AccountManager) context.getBean("accMgr");
		
		AccountManager accMgr1 = (AccountManager) context.getBean("accMgr");
		
		if(accMgr == accMgr1) {
			System.out.println("same instance");
		} else {
			System.out.println("different instance");
		}

		accMgr1.create();
		}
	}
	
}
