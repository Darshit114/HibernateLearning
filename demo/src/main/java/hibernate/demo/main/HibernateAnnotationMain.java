package hibernate.demo.main;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.demo.model.Employee1;
import hibernate.demo.util.HibernateUtil;

public class HibernateAnnotationMain {

	public static void main(String[] args) {
		
		Employee1 emp = new Employee1();
		
		emp.setName("David");
		emp.setRole("Developer");
		emp.setInsertTime(new Date());
		
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		//Start transaction
		session.beginTransaction();
		
		//Save the model object
		session.save(emp);
		
		//Commit the transaction
		session.getTransaction().commit();
		System.out.println("Employee ID: " + emp.getId());
		
		sessionFactory.close();
	}
}
