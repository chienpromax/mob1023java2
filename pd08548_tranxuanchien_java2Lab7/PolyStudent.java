/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd08548_tranxuanchien_java2Lab7;

import static pd08548_tranxuanchien_java2Lab7.bai1.career.UDPM;

/**
 *
 * @author DELL
 */
public class PolyStudent {
    public static void main(String[] args) {
        PolyStudent s= new PolyStudent();
        s.name="tran xuan chien";
        s.career = bai1.career.valueOf("UDPM");
        s.print();
    }

    public String name;
    public bai1.career career;

    public void print() {
        System.out.println(">full name " + this.name);
        switch (career) {
            case UDPM:
                System.out.println(">career Ung dung phan mem");
                break;
            case TKTW:
                System.out.println(">career thiet ke trang web");
                break;
            case LTDD:
                System.out.println(">career lap trinh di dong");
                break;
            case TKDH:
                System.out.println(">career thiet ke do hoa");
                break;
            default:
                throw new AssertionError();
        }
    }
}
