
package bean;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MarkServ", urlPatterns = {"/MarkServ"})
public class MarkServ extends HttpServlet {
@EJB MarksBean obj;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            String name = request.getParameter("student");
            String sub1 = request.getParameter("sub1");
            String sub2 = request.getParameter("sub2");
            String sub3 = request.getParameter("sub4");
       
            obj.addMarks(name,sub1,sub2,sub3);
            out.print("servlet done");
    }

}
