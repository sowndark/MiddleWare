
package niit.backendfinal.dao;

import java.util.List;

import niit.backendfinal.model.Employee;

public interface EmployeeDAO {
	

		public List<Employee> getemplist(); 
		public Employee getemp(int empid); 
		public boolean addemp(Employee emp); 
		public boolean updateemp(Employee emp);
		 public boolean deleteemp(int empid); 
		public Employee findById(int empid);
		

}

