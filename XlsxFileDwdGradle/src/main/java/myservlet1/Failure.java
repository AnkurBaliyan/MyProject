package myservlet1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Failure")
public class Failure extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><style>");
		out.println(
				" body {background-image:url(\"https://ak3.picdn.net/shutterstock/videos/9144695/thumb/4.jpg\");\n	background-size:100%;\n}");
		out.println("</style>");
		out.println("<body>");
		out.println(
				"<table table align=\"center\" width=\"40%\"  style=\" background-color:white; border: 3px inset #FFB975; border-radius: 15px; box-shadow:15px 10px 15px 2px #FFB975;\"> <tr> <td align=\"center\">");

		out.println("You have connected successfully with database");
		out.println("<br/>");
		out.println("<br/>");
		out.println("<br/>");
		out.println("<br/>");
		out.println("But there is no matching data in database");

		out.println(" </td> </tr> </table>");
		out.println("</body></html>");

	}

}
