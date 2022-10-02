
package pk1;

public class Userbean {
    
    private String name, age , hob ,email, gender, error ;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the hob
     */
    public String getHob() {
        return hob;
    }

    /**
     * @param hob the hob to set
     */
    public void setHob(String hob) {
        this.hob = hob;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
    
    public boolean validate(){
        boolean res = true;
        if(name == null){
            error += "<br> Enter Name <br>";
            res = false;
        }
        if(Integer.parseInt(age) <=0 ){
            error += "<br> Invalid Age <br>";
            res = false;
        }
        return res;
    }
}
