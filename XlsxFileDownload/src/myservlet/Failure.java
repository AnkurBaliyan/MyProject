package myservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Failure
 */
@WebServlet("/Failure")
public class Failure extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println("You have connected successfully with database");
		out.println("/n");
		out.println("/n");
		out.println("/n");
		out.println("/n");
		out.println("But there is no matching data in database");

	}

}
