package hibernate.demo.main;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.demo.model.Employee1;
import hibernate.demo.util.HibernateUtil;

public class HibernateJavaConfigMain {

	public static void main(String[] args) {
		
		Employee1 emp = new Employee1();
		
		emp.setName("Lisa");
		emp.setRole("Manager");
		emp.setInsertTime(new Date());
		
		SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		//Begin Transaction
		session.beginTransaction();
		
		//Save the object
		session.save(emp);
		
		//Commit the transaction
		session.getTransaction().commit();
		System.out.println("Employee ID: " + emp.getId());
		
		sessionFactory.close();
	}
}
