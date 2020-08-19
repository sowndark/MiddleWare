package niit.backendfinal.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import niit.backendfinal.model.Employee;

@Repository("empDAO")
@Transactional
public class EmployeeImp implements EmployeeDAO 
{
	
		@Autowired
		private SessionFactory sessionFactory;
        
		@Override
		public List<Employee> getemplist() {
			// TODO Auto-generated method stub
			return sessionFactory.getCurrentSession().createQuery("from Employee").list();
		}
		@Override
		public Employee getemp(int empid) {
			Session currentSession = sessionFactory.getCurrentSession();
			Query createQuery = currentSession.createQuery("from Employee where id="+empid);
			Object uniqueResult = createQuery.uniqueResult();
			return (Employee) uniqueResult;
		}
		@Override
		public boolean addemp(Employee emp) {
			try
		     {
		    	System.out.println("in Dao"); 
				sessionFactory.getCurrentSession().save(emp);
		    	 
		    	 return true;
      		}
		     catch(Exception e) {
		    	 return false;
		     }
		}
		@Override
		public boolean updateemp(Employee emp) {
			try
			{
				sessionFactory.getCurrentSession().update(emp);
				return true;
			}
			catch(Exception e)
			{
				return false;
			}
		}
		@Override
		public boolean deleteemp(int empid) {
			try
			{
				System.out.println("inside dao deleteEmployee");
				sessionFactory.getCurrentSession().delete(findById(empid));
				return true;
			}
			catch(Exception e)
			{
				return false;
			}
		}
		@Override
		public Employee findById(int empid) {
			// TODO Auto-generated method stub
			 return(Employee)sessionFactory.getCurrentSession()
					.createQuery("from Employee where id="+empid).uniqueResult();
		}

}

		