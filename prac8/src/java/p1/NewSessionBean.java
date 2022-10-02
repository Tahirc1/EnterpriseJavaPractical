
package p1;

import javax.ejb.Singleton;

@Singleton
public class NewSessionBean {
    public int count = 1;
    
    public int incCount(){return count++;}
   
}
