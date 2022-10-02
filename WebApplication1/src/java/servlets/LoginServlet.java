package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
            
            String email = request.getParameter("email");
    		String password = request.getParameter("password");
            
    		String query = "SELECT * FROM user WHERE email = ? ";
    		
    		PreparedStatement pstate = conn.prepareStatement(query);
    		pstate.setString(1, email);
    		
    		ResultSet set = pstate.executeQuery();
    		
    		 while(set.next())
             {
                 int id = set.getInt(1);
//                 String gname = set.getString(2);
//                 String gemail = set.getString(3);
                 String gpassword = set.getString(4);
                 
                 if(gpassword.equals(password)) {
                	 Cookie ck = new Cookie("social_id", id + "");
                	 ck.setHttpOnly(true);
                	 response.addCookie(ck);
                	 response.sendRedirect("./Posts");
                 } else {
                	 out.println("<link type=\"text/css\" rel=\"stylesheet\"  href=\"./css/style.css\"> <h1>Wrong Credentials</h1>"); 
                	 out.println("<a href='./login.html'>Try Again</a>"); 
                 }

             }
            
		}catch(Exception e) {
			e.printStackTrace();
			out.println("Error Occured");
			out.println(e.getMessage()); 
		}
		
	}
}
