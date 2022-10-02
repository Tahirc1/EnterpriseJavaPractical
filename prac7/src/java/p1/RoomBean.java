
package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.ejb.Stateless;


@Stateless
public class RoomBean {
    public void BookRoom(String name, String type, String num) {
       try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Hotel","root","root");
            PreparedStatement ps1= con.prepareStatement("Select * from Room where type=? and status='Not Booked'");
            ps1.setString(1,type);
            ResultSet rs = ps1.executeQuery();
            if(rs.next()){
                String rmno = rs.getString(1);
             System.out.print("-------- working");   
              PreparedStatement ps = con.prepareStatement("update Room set name=? , conno=? , status='Booked' where roomno = ?");
              ps.setString(2, num);
              ps.setString(1, name);
               ps.setString(3, rmno);
              ps.executeUpdate();
              System.out.print("-------- working update");   
            }
        }catch(Exception e){
            System.out.print("--------"+e);
        }  
    }
}
