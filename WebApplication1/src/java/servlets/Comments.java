package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


@WebServlet(name = "Comments", urlPatterns = {"/Comments"})
public class Comments extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html");
		PrintWriter out = response.getWriter();
                Connection conn;
                try{
			Class.forName("com.mysql.jdbc.Driver");
			
			String  user = "root";
                        String db_password = "root";
                        String url = "jdbc:mysql://localhost:3306/social";
                        conn = DriverManager.getConnection(url, user, db_password);
                        String p_id = request.getParameter("post") ;
                        String query = "Select user.id , user.name , comment.u_id , comment.u_comment from user , comment where p_id ="+ p_id + " AND user.id = comment.u_id";
                        Statement st = conn.createStatement(); 
                        ResultSet set = st.executeQuery(query);
                        out.println("<link type='text/css' rel='stylesheet'  href='./css/style.css'>" );
                        out.println("<body bgcolor='orange'> <nav>\n" +
"            <div>\n" +
"                <h1>Zocial</h1>\n" +
"            </div>\n" +
"            <div>\n" +
"                <ul>\n" +
"                    <li><a class=\"navButton active\" href=\"./register.html\">Register</a></li>\n" +
"                    <li><a class=\"navButton\" href=\"./login.html\">Login</a></li>\n" +
"                    <li><a class=\"navButton\" href=\"./post.html\">Create&nbsp;Post</a></li>\n" +
"                    <li><a class=\"navButton\" href=\"./Posts\">Explore&nbsp;Post</a></li>\n" +
"                </ul>\n" +
"            </div>\n" +
"        </nav><div class='max'><div class='he'><div class='postman'><div class='n2'>");
   
                        while(set.next()){
                            String comment = set.getString(4);
                            String user_name = set.getString(2);
                            out.println("<div class='comt'>"
                                    + "<h1 align='center'> All comments</h1>");
                            out.write("<p align='center'>"+user_name +":"+ comment +"<br></p>");
                            out.println("</div>");
                        }      
                        out.println("</div></div></div></div></body>");
                        
                }catch(Exception e){
                    out.println(e);
                 }
    }
}
