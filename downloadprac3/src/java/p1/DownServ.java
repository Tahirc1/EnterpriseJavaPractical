
package p1;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DownServ", urlPatterns = {"/DownServ"})
public class DownServ extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String fileName = request.getParameter("filename");
        
        ServletContext ctx = getServletContext();
        InputStream is = ctx.getResourceAsStream("/WEB-INF/"+fileName);
        response.setHeader("Content-disposition","attachment;filename=\""+fileName+"\"");
        int i;
        while((i = is.read()) !=-1){
            out.write(i);
        }
        is.close();
        out.close();
 
    }
}
