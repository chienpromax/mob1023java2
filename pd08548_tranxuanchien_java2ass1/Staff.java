/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd08548_tranxuanchien_java2ass1;

/**
 *
 * @author DELL
 */
public class Staff {
   public String staffID;
   public String name;
   public double age;
   public String email;
   public double salary;

    public Staff() {
    }

    public Staff(String staffID, String name, double age, String email, double salary) {
        this.staffID = staffID;
        this.name = name;
        this.age = age;
        this.email = email;
        this.salary = salary;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
   
   
   
}
