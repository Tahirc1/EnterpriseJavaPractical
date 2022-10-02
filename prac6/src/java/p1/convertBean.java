
package p1;

import javax.ejb.Stateless;

@Stateless
public class convertBean {
    private String s1;
    public double d2r(int amt) {
       return amt*80;
    }

    public double r2d(int amt) {
        return amt/80;
    }

    /**
     * @return the s1
     */
    public String getS1() {
        return s1;
    }

    /**
     * @param s1 the s1 to set
     */
    public void setS1(String s1) {
        this.s1 = s1;
    }

    
}
