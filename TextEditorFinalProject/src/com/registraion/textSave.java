package com.registraion;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/save")
public class textSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*
		PrintWriter out = response.getWriter();
		out.print("working");
		*/
		
		String text = request.getParameter("editor1");
		String uemail = request.getParameter("email");
		
		RequestDispatcher dispatcher = null;
		Connection con = null;
		
		/*
		PrintWriter out = response.getWriter();
		out.print(uname);
		out.print(uemail);
		out.print(upwd);
		out.print(umobile);
		*/
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_3876ebb1e227ccc","bab413135842e6","ac27bfdd");
			
			System.out.println(uemail);
			//PreparedStatement pst = con.prepareStatement("insert into textsave(text) values(?) where textsave.uemail = 'akkuchathu@gmail.com'");
			//PreparedStatement pst = con.prepareStatement("insert into textsave(text) values(?) ");
			PreparedStatement pst = con.prepareStatement("UPDATE textsave SET text=? WHERE uemail=?");
			//"update textsave set uemail='"+uemail+"',text='"+text+"' where uemail = '"+uemail+"'";
			//INSERT INTO textsave(text) VALUES (value) WHERE uemail = '"+uemail '";
			pst.setString(1, text);
			pst.setString(2, uemail);
			
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("generic.html");
			if(rowCount > 0) {
				request.setAttribute("status", "success");
				
			}else {
				request.setAttribute("status", "failed");
				
			}
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
