
package p1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "reqServ", urlPatterns = {"/reqServ"})
public class reqServ extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Cookie ck = new Cookie("name","tahir");
        response.addCookie(ck);
        Cookie[] m = request.getCookies();
        HttpSession s = request.getSession(true);
        s.setAttribute("name","tahir");
        
        String name= request.getParameter("uname");
        String pass = request.getParameter("pass");
        
        if(name.equals("admin") && pass.equals("admin")){
            RequestDispatcher rd = request.getRequestDispatcher("wel.html");
            rd.forward(request, response);
        }else{
            out.print("wrong password and uname");
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.include(request, response);
        }
    }

}
