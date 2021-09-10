package app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import employees.EmployeeDao;

public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanFile.xml");
		EmployeeDao ed = (EmployeeDao) context.getBean("employeeDao");
		ed.login("alec", "hello");
	}
}
