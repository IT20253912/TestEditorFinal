package com.registraion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String secretKey = "secrete";
		Encrypt aesEncryptionDecryption = new Encrypt();
		
		String uemail = request.getParameter("username");
		String upwd = request.getParameter("password");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		//String originalString = upwd;
		//String encryptedString = aesEncryptionDecryption.encrypt(originalString, secretKey);
		String originalString = upwd;
		String encryptedString = aesEncryptionDecryption.encrypt(originalString, secretKey);
		//String decryptedString = aesEncryptionDecryption.decrypt(encryptedString, secretKey);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_3876ebb1e227ccc","bab413135842e6","ac27bfdd");
			PreparedStatement pst = con.prepareStatement("select*from users where uemail = ? and upwd = ?");
			pst.setString(1, uemail);
			pst.setString(2, encryptedString);
			
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				session.setAttribute("name", rs.getString("uname"));
				dispatcher = request.getRequestDispatcher("index.html");
			}else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
