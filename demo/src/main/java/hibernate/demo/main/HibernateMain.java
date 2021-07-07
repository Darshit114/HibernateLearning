package hibernate.demo.main;

import java.util.Date;

import org.hibernate.Session;

import hibernate.demo.model.Employee;
import hibernate.demo.util.HibernateUtil;

public class HibernateMain {

	public static void main(String[] args) {
		
		Employee emp = new Employee();
		
		emp.setName("Elon");
		emp.setRole("CEO");
		emp.setInsertTime(new Date());
		
		//Get Session
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		//Start transaction
		session.beginTransaction();
		
		//Save the Model object
		session.save(emp);
		
		//Commit trnsaction
		session.getTransaction().commit();
		
		System.out.println("Employee Id: " + emp.getId());
		
		HibernateUtil.getSessionFactory().close();
	}
}
