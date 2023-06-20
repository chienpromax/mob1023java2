/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java2Lab1;

/**
 *
 * @author DELL
 */
public class appProductBai3 {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            product p1;
            if (i != 0) {
                p1 = new product();
            } else {
                p1 = new noTexProduct();
            }
            p1.input();
            p1.output();
        }
    }
}
