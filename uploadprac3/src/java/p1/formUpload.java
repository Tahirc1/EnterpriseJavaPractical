
package p1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@MultipartConfig 
@WebServlet(name = "formUpload", urlPatterns = {"/formUpload"})
public class formUpload extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String path = request.getParameter("path");
        
        Part file = request.getPart("file");
        String fileName = file.getSubmittedFileName().toString();
        out.print("FileName:"+ fileName);
        try{
            OutputStream os = new FileOutputStream(path + "/"+ fileName);
            InputStream is = file.getInputStream();
            int read=0;
            while((read = is.read())!= -1){
                os.write(read);
            }
            out.print("upload Successfull");
        }catch(Exception e){
            out.print(e);
        }
    }

}
