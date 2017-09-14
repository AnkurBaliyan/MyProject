package hibernetUtilPackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;

import hibernetCRUDPackage.Employee;

@WebServlet("/")
public class CreateData extends HttpServlet {

	org.slf4j.Logger log = LoggerFactory.getLogger(CreateData.class);
	private static SessionFactory factory;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Session session = factory.getCurrentSession();
		org.hibernate.Transaction tr = session.beginTransaction();
		Query qry = session.createQuery("update Employee e set e.empName='vipul' where e.id=4");
		qry.executeUpdate();
		Employee emp = new Employee();
		emp.setId(46);
		emp.setEmpName("Ankur Kumar");
		emp.setEmpMobileNos("000000");
		emp.setEmpAddress("Delhi - India");
		session.save(emp);
		tr.commit();
		log.debug("Successfully inserted");

		response.getWriter().append("Successful: ").append(request.getContextPath());
	}

}