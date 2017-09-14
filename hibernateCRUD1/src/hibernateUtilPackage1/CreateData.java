package hibernateUtilPackage1;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import hibernateCRUDPackage.Employee;

public class CreateData {
	private static SessionFactory sessionFactory;
	//static Query qry;
	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		configuration.configure();
		sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		org.hibernate.Transaction tr = session.beginTransaction();
		//qry = session.createQuery("update Employee e set e.empName='pankaj' where e.id=4");
		//qry = session.createSQLQuery("insert into Employee values(3,'Manish','Muzaffarnagar','8750808856')");
		// qry=session.createQuery("delete from Employee where
		// empAddress='8750808856'");
		//System.out.println(qry);
		//qry.executeUpdate();
		Employee emp = new Employee();
		emp.setId(3);
		emp.setEmpName("Kusdfamar");
		emp.setEmpMobileNos("8750808ddsfa856");
		emp.setEmpAddress("Indiadfsaa");
		session.save(emp);
		tr.commit();
		System.out.println("Successfully inserted");

	}

}