package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/CreatePostServlet")
public class CreatePostServlet extends HttpServlet {
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
            
            
            String desc = request.getParameter("desc");
            Part file = request.getPart("image");
            
            Cookie[] cks = request.getCookies();
            if(cks == null) {
            	out.println("<h1>Not Logged In</h1>");
            	out.println("<a href='./login.html'>Log In</a>");
            	return;
            }
            String user_id = cks[0].getValue();
            
            String[] hdrs = file.getHeader("content-disposition").split("=");
            for(int i =0; i < hdrs.length ;i++){
            out.println( hdrs[i] + "<br>");}
            String fileName = hdrs[hdrs.length - 1];
            out.println(fileName);
            
            ServletContext s_ctx = getServletContext();
            
            InputStream is = file.getInputStream();
            byte[] data = new byte[is.available()];
            is.read(data);
            String outFilePath = s_ctx.getRealPath("/")  + "images" + File.separator + fileName;
            outFilePath = outFilePath.replace("\"","");
            out.println(outFilePath + "<br>");
            FileOutputStream fout = new FileOutputStream(outFilePath);
            fout.write(data);
            
            String query = "insert into post(createdBy, image, description) values(?, ?, ?)";
            
            PreparedStatement pstate = conn.prepareStatement(query);
            pstate.setString(1, user_id);
            pstate.setString(2, outFilePath);
            pstate.setString(3, desc);
            
            pstate.executeUpdate();
            
            response.sendRedirect("./Posts");
            
    		fout.close();  
		} catch (Exception e) {
			e.printStackTrace();
			out.println("Error Occured");
			out.println(e.getMessage());
		}
		
		
	}

}
