/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd08548_tranxuanchien_lab6;

/**
 *
 * @author DELL
 */
public class TestThread {
    public static void main(String[] args){
        OddThread o = new OddThread();
        Thread t =new Thread(o);
        t.start();
        try {
        t.join();
        } catch (Exception e) {
            System.out.println(""+e);
        }
        
        EvenThread e= new EvenThread();
        Thread t1= new Thread(e);
        t1.start();
    }
    
}
