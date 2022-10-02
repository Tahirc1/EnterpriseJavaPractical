package p1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RoomServ", urlPatterns = {"/RoomServ"})
public class RoomServ extends HttpServlet {
@EJB RoomBean obj;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String num = request.getParameter("num");
        out.print(name+type+num);
        obj.BookRoom(name,type,num);
    }
}
