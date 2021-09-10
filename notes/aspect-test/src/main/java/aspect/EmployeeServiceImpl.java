package aspect;

public class EmployeeServiceImpl implements EmployeeService{

	@Override
	public void create() {
		System.out.println("create called");
	}

	@Override
	public void findById() {
		System.out.println("find by id called");
		
	}

	
	
}
