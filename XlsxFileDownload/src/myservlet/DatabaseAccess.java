package myservlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@WebServlet("/DatabaseAccess")
public class DatabaseAccess extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger log = LoggerFactory.getLogger("");

		Connection connection = null;
		PreparedStatement stmt=null;
		ResultSet rs1 =null;
		PrintWriter out = response.getWriter();
		try {

			out.println("<html><style>");
			out.println(
					" body {background-image:url(\"https://ak3.picdn.net/shutterstock/videos/9144695/thumb/4.jpg\");\n	background-size:100%;\n}");
			out.println("</style>");
			out.println("<body>");
			

			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/reports", "root", "geek001");
			log.info("Got Connection");
			// Statement stmt1 = connection.createStatement();

			out.println(
					"<table table align=\"center\" width=\"40%\"  style=\" background-color:white; border: 3px inset #FFB975; border-radius: 15px; box-shadow:15px 10px 15px 2px #FFB975;\"> <tr> <td align=\"center\">");
			

		    stmt = connection.prepareStatement("select * from mydb where service_id=? and start_time>= ? and start_time<=? ");
			int ServiceId = Integer.parseInt(request.getParameter("service_id"));

            String Sdate=request.getParameter("Sdate")+":00";
            String StartingDate=Sdate.replace('T',' ');
            
            String Edate=request.getParameter("Edate")+":00";
            String EndingDate=Edate.replace('T',' ');
            
            
			stmt.setInt(1, ServiceId);
			stmt.setTimestamp(2, java.sql.Timestamp.valueOf(StartingDate));
			stmt.setTimestamp(3, java.sql.Timestamp.valueOf(EndingDate));
			log.info(stmt.toString());

		    rs1 = stmt.executeQuery();

			if (!rs1.next()) {
				
				 RequestDispatcher rd=request.getRequestDispatcher("Failure");  
				 rd.forward(request, response);  
				
				
			}

			else {
				out.println("You have connected successfully with database");
				out.println("<br/>");
				out.println("<br/>");
				out.println("<br/>");
				out.println("<br/>");
				out.println("Starting Date :" + request.getParameter("Sdate"));
				out.println("<br/>");
				out.println("End Date:" + request.getParameter("Edate"));
				out.println("<br/>");
				out.println("Service Id is :" + request.getParameter("service_id"));
				out.println("<br/>");
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet spreadsheet = workbook.createSheet();
				XSSFRow row = spreadsheet.createRow(0);
				XSSFCell cell;
				cell = row.createCell(0);
				cell.setCellValue("id");
				cell = row.createCell(1);
				cell.setCellValue("service_id");
				cell = row.createCell(2);
				cell.setCellValue("start_time");
				cell = row.createCell(3);
				cell.setCellValue("end_time");
				cell = row.createCell(4);
				cell.setCellValue("name");
				cell = row.createCell(5);
				cell.setCellValue("call_trxn_id");
				cell = row.createCell(6);
				cell.setCellValue("disposition");
				int i = 1;

				do {

					log.info(rs1.getInt(1) + " " + rs1.getInt(2) + " " + rs1.getString(3) + " " + rs1.getDate(4) + " "
							+ rs1.getString(5) + " " + rs1.getString(6) + " " + rs1.getString(7));
					row = spreadsheet.createRow(i);
					cell = row.createCell(0);
					cell.setCellValue(rs1.getInt("id"));
					cell = row.createCell(1);
					cell.setCellValue(rs1.getInt("service_id"));
					cell = row.createCell(2);
					cell.setCellValue(rs1.getString("start_time"));
					cell = row.createCell(3);
					cell.setCellValue(rs1.getString("end_time"));
					cell = row.createCell(4);
					cell.setCellValue(rs1.getString("name"));
					cell = row.createCell(5);
					cell.setCellValue(rs1.getString("call_trxn_id"));
					cell = row.createCell(6);
					cell.setCellValue(rs1.getString("disposition"));
					i++;
				} while (rs1.next());

				out.println("<br/>");
				out.println("<br/>");
				out.println("<br/>");

				out.println("Download XLS sheet of above DATA");
				FileOutputStream pout = new FileOutputStream(
						new File("/var/www/html/Service_id=" + request.getParameter("service_id") + ".xlsx"));
				workbook.write(pout);

				out.println("<br/>");
				out.println("<a href=\"http://localhost/Service_id=" + request.getParameter("service_id")
						+ ".xlsx\" targate=\"_blank\">Download</a>");
				out.println("<br/>");

			}

			rs1.close();
			stmt.close();
			connection.close();

			out.println(" </td> </tr> </table>");
			out.println("</body></html>");
		} catch (Exception e) {
			log.info("" + e);
		}finally {
		    if (rs1!= null) {
		        try {
		            rs1.close();
		        } catch (SQLException e) {log.info("" + e); }
		    }
		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException e) { log.info("" + e);}
		    }
		    if (connection != null) {
		        try {
		            connection.close();
		        } catch (SQLException e) {log.info("" + e);}
		    }
		}
   
		
	}

}