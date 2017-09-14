package com.sparktg;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@LocalBean
@Singleton
@Startup
@Path("/")
// @Produces(MediaType.APPLICATION_JSON)

@Produces({ "application/json" })
public class MainClass {
	private static Logger log = LoggerFactory.getLogger("");
	static float netCharge ;

	@GET
	@Path("/display")
	@Consumes({ "application/json" })
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Object> getData(@QueryParam("id") int id,@QueryParam("name") int id2) throws ClassNotFoundException, SQLException, IOException {
        int count=0;
        Class.forName("org.postgresql.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/reports", "root", "geek0011");
		//127.0.0.1:database name(root or anything )
		
		if (connection != null) {

			log.debug("connected");
		} else {
			log.debug("there is error");

		}

		Statement stmt1 = connection.createStatement();
		//stmt1.executeUpdate("update student set name ='Ankur Baliyan' where name ='ankur'");
		
		ResultSet rs1 = stmt1.executeQuery("select * from mydb where service_id>="+id+" and service_id<="+id2);
		
		ResultSetMetaData rsmd = rs1.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		
		for (int i=1;i<=columnsNumber;i++)
		{
		String columnsName=rsmd.getColumnName(i);
		log.debug(i+ " column name is :"+columnsName);
		}
		log.debug("Total no of column is "+columnsNumber);
		
		
		ArrayList<Object> arrayList = new ArrayList<Object>(); 
		while (rs1.next()) {
		
		log.debug(rs1.getInt(1) + " " + rs1.getInt(2)+" "+rs1.getDate(3)+" "+rs1.getDate(4)+" "+rs1.getString(5));
		 count++;
		 arrayList.add(rs1.getInt(1));
		 
		 arrayList.add(rs1.getInt(2));
		 arrayList.add(rs1.getDate(3));
		 arrayList.add(rs1.getDate(4));
		 arrayList.add(rs1.getString(5));
		
		}
		log.debug("total no of raw in ArrayList is : "+count);
		for (Object a: arrayList)
		{
			log.debug(a+"");
		}
		return arrayList;

	}

	@POST
	@Path("/con")
	// @Consumes({"application/json"})
	@Produces(MediaType.APPLICATION_JSON)
	public String postgres(@FormParam("id") int id, @FormParam("name") String name)
			throws ClassNotFoundException, SQLException {

		Class.forName("org.postgresql.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/root", "root", "geek0011");
		if (connection != null) {

			log.debug("connected");
		} else {
			log.debug("there is error");
			return "connection is establish \n";

		}
		Statement stmt = connection.createStatement();
		String sql = null;
		sql = "insert into student values (" + id + "," + name + ")";
		stmt.executeUpdate(sql);

		/*
		 * sql="insert into student values (6,'vipul')";
		 * stmt.executeUpdate(sql);
		 */

		return "connection is establish \n";

	}

	@POST
	@Path("/post1")
	public String ping() {
		log.debug("ping method called");

		return "pong";
	}

	@POST
	@Path("/add")
	public Response addUser(@FormParam("id") int id, @FormParam("name") String name, @FormParam("price") float price) {

		log.debug(" Product added successfuly!<br> Id: " + id + "<br> Name: " + name + "<br> Price: " + price);

		return Response.status(200)
				.entity(" Product added successfuly!<br> Id: " + id + "<br> Name: " + name + "<br> Price: " + price)
				.build();

	}

	@GET
	@Path("/code")
	public int pulseCount(@QueryParam("Number") int Number) {
		log.debug("Factorial of  " + Number);
		int b = 1;

		if (Number == 0) {
			return 1;
		} else {
			for (int i = 1; i <= Number; i++) {
				b = b * i;
			}
			log.debug("I am from trace");
			log.debug("is " + b);
			return b;
		}

	}

	@GET
	@Path("/Calculation")
	@Consumes({ "application/json" })
	@Produces(MediaType.APPLICATION_JSON)
	public  double calculation() {
		int pulseRate = 60;
		int totalPulse = 0;
		float pulseCharge = (float) 2;
		DecimalFormat df = new DecimalFormat("#.###");
		try {
			BufferedReader br = new BufferedReader(new FileReader("/home/sparkuser/Documents/ankur.txt"));
			String line = null;
			while ((line = br.readLine()) != null) {
				int duration = Integer.parseInt(line);
				if (duration % pulseRate == 0) {
					totalPulse = duration / pulseRate;
					netCharge += totalPulse * pulseCharge;
				} else {
					totalPulse = duration / pulseRate + 1;
					netCharge += totalPulse * pulseCharge;
				}
			}
			log.debug("Final NetCharge is : " + netCharge);
			float serviceTex = (float) ((float) netCharge * 0.14);
			float swachBhartTex = (float) ((float) netCharge * 0.05);
			float krishiTex = (float) ((float) netCharge * 0.05);
			float totalAmount = netCharge + serviceTex + swachBhartTex + krishiTex;

			log.debug("Service Tex: " + df.format(serviceTex));
			log.debug("Swach Bhart Tex: " + df.format(swachBhartTex));
			log.debug("Krishi Tex: " + df.format(krishiTex));
			log.debug("Total Amount : " + df.format(totalAmount));
		} catch (Exception e) {
			log.debug("there is error");

			log.debug("unable to read file" + e.getMessage());
		}
		return netCharge;

	}
}