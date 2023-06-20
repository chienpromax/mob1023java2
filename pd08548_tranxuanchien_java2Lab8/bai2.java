/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd08548_tranxuanchien_java2Lab8;

import java.util.ArrayList;
import java.util.Scanner;
import java2Lab1.appProduct;

/**
 *
 * @author DELL
 */
public class bai2 {
    public static void main(String[] args) {
        
        ArrayList<Integer> myaray = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);
        int[] a = new int[5];
        for (int i = 0; i < 5; i++) {
            System.out.println("nhap vao so: ");
            a[i]=sc.nextInt();
        }
        for (int i : a) {
            System.out.println(""+i);
        }
    }
}
