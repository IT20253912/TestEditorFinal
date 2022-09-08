<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        
        
    </head>
    <body>
        <div class="container">
            
            
                    <div class="form-group">
                        <h2>Find City</h2>
                    </div>
                <br>
                <div class="form-group">

            <form method="get" action="find">    
                      <div class="form-group">
                          <h3>Select the City</h3>
       <%
            Connection con;
            PreparedStatement pst;
            ResultSet rs;
         try
{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/melwa","root","");
            pst = con.prepareStatement("select distinct city from employee");
    rs=pst.executeQuery();
            
            if(rs.next())
   {
      out.println("<tr>");
              out.println("<td>Choose City Name</td>");
      out.println("<td>");
      out.println("<select name='combo'>");
  do{
            String cityname = rs.getString("city");
    out.println("<option value='"+ cityname   +"'>" +  cityname +  "</option>");
          }
            while(rs.next());
   out.println("</select>");
           rs.close();
   out.println("</td></tr>");
           out.println("</tr><td colspan=2 align=center><input type=submit  value=Retrieve></td></tr>");
           }
           else
            {
           out.println("<tr>");
   out.println("<td colspan=2 align=right>");
   out.println("Sorry table is Empty");
           out.println("</td>");
            }
 
   out.println("</form>");
  }
        catch(Exception e)
        {
                 e.printStackTrace(); // Or System.out.println(e);
        }
                              
        %>
         </div>
                    
          
                    
          </form>                    
                    
              </div>      
              
     </div>
</div>
 
    </body>
</html>