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


@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//start
	
	
	
//end

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//start
		final String secretKey = "secrete";
		
		 Encrypt aesEncryptionDecryption = new Encrypt();

	    // String decryptedString = aesEncryptionDecryption.decrypt(encryptedString, secretKey);
	     
	    // System.out.println(originalString);
	     //   System.out.println(encryptedString);
	    //    System.out.println(decryptedString);
	     
	     //end
	      
	     
		/*
		PrintWriter out = response.getWriter();
		out.print("working");
		*/
		
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String upwd = request.getParameter("pass");
		String umobile = request.getParameter("contact");
		RequestDispatcher dispatcher = null;
		Connection con = null;
		
		/*
		PrintWriter out = response.getWriter();
		out.print(uname);
		out.print(uemail);
		out.print(upwd);
		out.print(umobile);
		*/
		String originalString = upwd;
		 String encryptedString = aesEncryptionDecryption.encrypt(originalString, secretKey);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_3876ebb1e227ccc","bab413135842e6","ac27bfdd");
			PreparedStatement pst = con.prepareStatement("insert into users(uname,upwd,uemail,umobile) values(?,?,?,?)");
			PreparedStatement psta = con.prepareStatement("insert into textsave(uemail) values(?)");
			pst.setString(1, uname);
			pst.setString(2, encryptedString);
			pst.setString(3, uemail);
			pst.setString(4, umobile);
			psta.setString(1, uemail);
			
			int rowCount = pst.executeUpdate();
			int rowCounta = psta.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
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
