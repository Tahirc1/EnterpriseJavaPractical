package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Posts")
public class Posts extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection conn;
		try {
	    Class.forName("com.mysql.jdbc.Driver");
			
	    String  user = "root";
            String db_password = "root";
            String url = "jdbc:mysql://localhost:3306/social";
            conn = DriverManager.getConnection(url, user, db_password);
            
            String query = "Select post.p_id, name, image, description FROM post, user WHERE user.id = post.createdBy";
            Statement st = conn.createStatement(); 
            ResultSet set = st.executeQuery(query);
            
            out.println("<link type='text/css' rel='stylesheet'  href='./css/style2.css'>"); 
            out.println("<nav>\n" +
"            <div>\n" +
"                <h1><a href='./index.html'>Zocial</a></h1>\n" +
"            </div>\n" +
"            <div>\n" +
"                <ul>\n" +
" <li><a class=\"navButton active\" href=\"./register.html\">Register</a></li>\n" +
"                    <li><a class=\"navButton\" href=\"./login.html\">Login</a></li>  "+
"                    <li><a class=\"navButton\" href=\"./post.html\">Create&nbsp;Post</a></li>\n" +
"                    <li><a class=\"navButton\" href=\"./Posts\">Explore&nbsp;Post</a></li>\n" +
"                </ul>\n" +
"            </div>\n" +
"        </nav> <div class='max'>"+
"  <div class=\"he\"> "+
                    "<div class='postman'>");
            while(set.next())
            {
            	int post_id = set.getInt(1);
                String creator = set.getString(2);
                String Location = set.getString(3);
                String desc = set.getString(4);
                String l_query = "SELECT COUNT(*) FROM likes WHERE p_id = " + post_id;
                Statement l_st = conn.createStatement();
                ResultSet l_set = l_st.executeQuery(l_query);
                l_set.next();
                String total_likes = l_set.getString(1);
                String[] Loc_list = Location.split("\\\\"); 
                
                String source = Loc_list[Loc_list.length - 1];
                out.println("<div class='n2'>");
                //out.println(source);
                out.println("Posted By " + creator);
                out.println("<img height='300' width='300' id='post-"+ post_id +"' src='./images/" + source + "'"+ " alt='"+ desc +"' />");
                out.println("Total Likes: " + total_likes);
                out.println("<div>");
                out.println("<form method='post'><textarea name='textc'> enter your comment </textarea> <input type='submit' name='coml' value='comment'> ");
                out.println("</div>");
                out.println("<div >");
                out.println("<input type='submit' name='coml' value='Checkcomments'>");
                out.println("<input type='hidden' name='post' value='"+ post_id +"' /><input type='submit' name='coml' value='like'></div></form>");
                out.println("</div>");
            }
            
		}catch(Exception e){
			e.printStackTrace();
			out.println("Error Occured");
			out.println(e.getMessage()); 
		}
                 out.println("</div></div></div>");
	}
//  add likes and comment to a post 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection conn;
		PrintWriter out = response.getWriter();
		String post = request.getParameter("post");
		Cookie[] cks = request.getCookies();
		if(cks == null) {
        	out.println("<h1>Not Logged In</h1>");
        	out.println("<a href='./login.html'>Log In</a>");
        	return;
        }
                String user_id = cks[0].getValue();
		out.println(post + " " + user_id);
		
	try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String  user = "root";
                String db_password = "root";
                String url = "jdbc:mysql://localhost:3306/social";
            
                conn = DriverManager.getConnection(url, user, db_password);
            
                if( request.getParameter("coml").equals("like") ){
                String query = "INSERT into likes(p_id, u_id) values(?, ?)";
                PreparedStatement pstate = conn.prepareStatement(query);
                pstate.setString(1, post);
                pstate.setString(2, user_id);
                pstate.executeUpdate();
                response.sendRedirect("./Posts#post-" + post);}
            
                if( request.getParameter("coml").equals("comment") ){
                    String u_comment = "u_comment";
                String comment = request.getParameter("textc");
                String query = "INSERT into comment(p_id, "+u_comment+",u_id) values(?, ?, ?)";
                PreparedStatement pstate = conn.prepareStatement(query);
                pstate.setString(1, post);
                pstate.setString(2, comment);
                pstate.setString(3,user_id);
                pstate.executeUpdate();
                response.sendRedirect("./Posts#post-" + post);}
                
                
                if( request.getParameter("coml").equals("Checkcomments") ){
                RequestDispatcher rd = request.getRequestDispatcher("Comments");
                rd.forward(request, response);
                }
		}catch(Exception e){
			e.printStackTrace();
			out.println("Error Occured");
			out.println(e.getMessage()); 
		}
	}
}
