
package p1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "convertServ", urlPatterns = {"/convertServ"})
public class convertServ extends HttpServlet {
@EJB convertBean obj;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String amount = request.getParameter("amount");
        String type = request.getParameter("type");
        int amt = Integer.parseInt(amount);
        if(type.equalsIgnoreCase("D2R")){
           out.print(obj.d2r(amt)); 
        }
        if(type.equalsIgnoreCase("R2D")){
            out.print(obj.r2d(amt));
        }
       
    }

}
