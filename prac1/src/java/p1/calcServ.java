package p1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author win10
 */
@WebServlet(name = "calcServ", urlPatterns = {"/calcServ"})
public class calcServ extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int num1 = Integer.parseInt(request.getParameter("num1"));
        int num2 = Integer.parseInt(request.getParameter("num2"));
        String btn = request.getParameter("btn");
        
        if(btn.equals("+")){
            out.print(num1+num2);
        }
        
         if(btn.equals("-")){
            out.print(num1-num2);
        }
         
          if(btn.equals("*")){
            out.print(num1*num2);
        }
          
           if(btn.equals("/")){
            out.print(num1/num2);
        }
        
    }

}
