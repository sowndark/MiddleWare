package niit.backendfinal.testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import niit.backendfinal.model.Employee;
import niit.backendfinal.service.EmplService;

public class Testing {

	static EmplService empService;
	
	@BeforeClass
	public static void initialize() {
		System.out.println("1");
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext();
		System.out.println("2");
		context.scan("niit.backendfinal");
		System.out.println("3");
	    context.refresh();
		System.out.println("Bean Initialization");
		empService = (EmplService) context.getBean("empService");
		System.out.println("Successfully bean initiated");
	}
	
	//@Ignore
	@Test
	public void addemptest() {
		Employee emp=new Employee();
		System.out.println("in");
		emp.setEmpid(1);
		emp.setName("lokesh");
		emp.setEmail("lokesk@gmail.com");
		emp.setPhone("56546636");
		System.out.println("before");
		empService.addemp(emp);
		System.out.println("Inside test");
	}
	
	@Ignore
	@Test
	public void listEmployeeDetails() {
		List<Employee> listemp=empService.getemplist();
		assertNotNull("problem in listing faculty:",empService.getemplist());
		for(Employee emp:listemp)
		{
			System.out.println(emp.getEmpid()+"||"+emp.getName()+"||"+emp.getEmail()+"||"+emp.getPhone()+"||");
			
		}
	}
	
     @Ignore
	@Test
	public void updateEmployeeDetails() {
		System.out.println("inside update method");
		Employee emp;
		System.out.println(" method");
		emp=empService.getemp(1);
	     System.out.println("after get");
		emp.setName("sowndar kumresan");
	
		System.out.println("after update method");
		 
		assertTrue("problem in updating faculty:",empService.updateemp(emp));
		
	}
	
	@Ignore
	@Test
	public void deleteemp() {
		//EmployeeDetails emp=empService.getEmployeeDetails(2);
		int empId=35;
		assertTrue("problem in deleting faculty:",empService.deleteemp(empId));
	}

}
