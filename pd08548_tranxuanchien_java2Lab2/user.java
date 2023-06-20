/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java2lab2;

/**
 *
 * @author DELL
 */
public class user {
    private String usename;
    private String pasword;
    private boolean role;

    public user() {
    }

    public user(String usename, String pasword, boolean role) {
        this.usename = usename;
        this.pasword = pasword;
        this.role = role;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
    
    
    
}
