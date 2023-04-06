package com.jrk.util.client;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.cfg.Environment;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.jrk.util.HibernateUtil;
import com.jrk.util.entities.Employee;

public class ClientTest {

	public static void main(String[] args) {

		try (Session session = HibernateUtil.getSessionFactory().openSession();) {

			//createEmployeeDB(session);
			int employeeId = 2;
			//readEmployeeeById(session ,employeeId);
			double newSal = 87000.0;
			readEmployeeSalaryById(session,newSal, employeeId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void readEmployeeSalaryById(Session session, double newSal, int employeeId) {

		Employee employee = session.get(Employee.class, employeeId);
		employee.setSalary(newSal);
		employee.setEmail("jagugduwguidwgu@gmail.com");
		session.beginTransaction();
		session.update(employee);
		session.getTransaction().commit();
	
		
	}

	private static void readEmployeeeById(Session session, int employeeId) {

		Employee employee = session.get(Employee.class, employeeId);
		System.out.println(employee);
	}

	private static void createEmployeeDB(Session session) {
		Employee employee = createEmployee();
		session.beginTransaction();
		Serializable serializable = session.save(employee);
		Integer id = (Integer)serializable; 
		session.getTransaction().commit();
		System.out.println("Employee is created with  ID" +id);
	}

	private static Employee createEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("john");
		employee.setEmail("john1266@gmail.com");
		employee.setSalary(60000.0);
		employee.setDoj(new java.sql.Date(0));
		return employee;
	}
}
