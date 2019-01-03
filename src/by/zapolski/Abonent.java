package by.zapolski;

public class Abonent extends Entity{
    private int phone;
    private String lastName;
    public Abonent(){

    }

    public Abonent(int id, int phone, String lastName) {
        super(id);
        this.phone = phone;
        this.lastName = lastName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString(){
        return "Abonent [id="+id+", phone="+phone+", lastName='"+lastName+"']";
    }
}
