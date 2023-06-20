/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package java2Lab1;

import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class product implements dao {

    private String name;
    private double pice;

    public product() {
    }

    public product(String name, double pice) {
        this.name = name;
        this.pice = pice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPice() {
        return pice;
    }

    public void setPice(double pice) {
        this.pice = pice;
    }

    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("input name");
        name = sc.nextLine();
        System.out.println("input pice");
        pice = sc.nextDouble();
    }

    public void output() {
        System.out.printf("name product: %s | pice product: %f | tax: %f\n", name, pice, getInportTax());
    }

    public void outputBai4() {
        System.out.printf("name product: %s | pice product: %f | tax: %f\n", name, pice, getInportTax());
        insert();
        update();
        delete();
        select();
    }

    public double getInportTax() {
        return pice *= 0.1;
    }

    @Override
    public void insert() {
        System.out.println("insert success");
    }

    @Override
    public void update() {
        System.out.println("update success");
    }

    @Override
    public void delete() {
        System.out.println("delete success");
    }

    @Override
    public void select() {
        System.out.println("select success");
    }

}
