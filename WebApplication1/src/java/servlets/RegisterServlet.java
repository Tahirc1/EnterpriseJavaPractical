package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String  user = "root";
            String db_password = "root";
            String url = "jdbc:mysql://localhost:3306/social";
            
            conn = DriverManager.getConnection(url, user, db_password);
            
            
            String name = request.getParameter("name");
    		String email = request.getParameter("email");
    		String password = request.getParameter("password");
    		String confirm_password = request.getParameter("confirm_password");
    		
    		if(!password.equals(confirm_password)) {
    			out.println("<p>Passwords does not match<p>");
    			out.println("<a href='./register.html'>Go back</a>");
    			return;
    		}
    		
    		String query = "insert into user(name,email,password) values(?,?,?)";
            
            PreparedStatement pstate;
            
            pstate = conn.prepareStatement(query);
			pstate.setString(1, name);
		    pstate.setString(2, email);
		    pstate.setString(3, password);
		        
		    // execute query
		    pstate.executeUpdate();
		    out.println("<link type=\"text/css\" rel=\"stylesheet\"  href=\"./css/style.css\"> <h1>User created</h1>" + "<br />");
		    out.println("<a href='./login.html'>Log In</a>");
            
		} catch (Exception e) {
			e.printStackTrace();
			out.println("Error Occured");
			out.println(e.getMessage()); 
		}
	
	}

}
