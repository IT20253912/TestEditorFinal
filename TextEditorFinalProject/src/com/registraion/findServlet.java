package com.registraion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/find")
public class findServlet extends HttpServlet
{
     int i;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try {
            i++;
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            String cityname= request.getParameter("combo");
 
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/melwa","root","");

            pst = con.prepareStatement("select empid,fname,title,address from employee where city=?");
            pst.setString(1, cityname);
            rs = pst.executeQuery();
            
             out.println("<table width=60% border= 1   >");
             out.println("<tr><td colspan=4 " );
             out.println("<center><h2>Result of Search Page</h2></center>");
             out.println("</td></tr>");
             out.println("<tr>");
             out.println("<th>Employee id</th>");
             out.println("<th>Employee name</th>");
             out.println("<th>Title</th>");
             out.println("<th>Address</th>");
             out.println("</tr>");
              
              while(rs.next())
              {
                  out.println("<tr>");
                  out.println("<td>" + rs.getString("empid") + "</td> ");
                  out.println("<td>" + rs.getString("fname") + "</td> ");
                  out.println("<td>" + rs.getString("title") + "</td> ");
                  out.println("<td>" + rs.getString("address") + "</td> ");
                  out.println("</tr>");
 
                  
              }
              out.println("</table>");
        
               }
            catch (ClassNotFoundException ex) {
          
        }catch (Exception e)
        { throw new ServletException("error", e); }
    }
    
    public void destory()
    {
        i = 0;
    }
 
    
}