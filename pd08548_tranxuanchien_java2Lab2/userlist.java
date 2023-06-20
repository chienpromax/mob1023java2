/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java2lab2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class userlist {
    public static List<user> ls = new ArrayList();
    public int add(user u){
        ls.add(u);
        return 1;
    }
    public int findUserbyID(String username){
        int pos = -1;
        for (int i = 0; i < ls.size(); i++) {
            user u =ls.get(i);
            if (u.getUsename().equals(username)) {
                pos = i;
                break;
            }
        }
        return pos;
    }
    public int del(String username){
        int pos = findUserbyID(username);
        if (pos<0) {
            System.out.println("khong tim thay");
        }else {
            ls.remove(pos);
            System.out.println("da xoa");
        }
        return pos;
    }
    public int update(user u){
        int pos = findUserbyID(u.getUsename());
        if (pos <0) {
            System.out.println("khong tim thay");
        }else{
            ls.set(pos, u);
        }
        return pos;
    }
    public List<user> getAll(){
        for (user u : ls) {
            System.out.println("->"+u.getUsename()+"_"+u.getPasword());
        }
        return ls;
    }
}
