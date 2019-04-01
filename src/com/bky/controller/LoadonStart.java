package com.bky.controller;



import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



@Controller
public class LoadonStart extends HttpServlet {
	@Autowired


	public void init() throws ServletException {/*
		InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/info.properties");
	
		
		  try {
			Class.forName("com.mysql.jdbc.Driver") ;
			 String url = "jdbc:sqlserver://localhost:1433; DatabaseName=YFERP_HH" ;    
		     String username = "sa" ;   
		     String password = "sa" ;	
		     Connection con =null;
		     Statement stmt = null;
		     con = (Connection) DriverManager.getConnection(url,username,password);
		     stmt = (Statement) con.createStatement();
	            String sql;
	            sql = "SELECT id, name, url FROM websites";
	            ResultSet rs = stmt.executeQuery(sql);
	            while(rs.next()){
	                // 通过字段检索
	                int id  = rs.getInt("id");
	                String name = rs.getString("name");
	                String url1 = rs.getString("url");
	    
	                // 输出数据
	                System.out.print("ID: " + id);
	                System.out.print(", 站点名称: " + name);
	                System.out.print(", 站点 URL: " + url1);
	                System.out.print("\n");
	            }
	            rs.close();
	            stmt.close();
	            con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		*/
		
		
		}

	
}
