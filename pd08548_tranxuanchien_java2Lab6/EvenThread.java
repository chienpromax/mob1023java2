/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd08548_tranxuanchien_lab6;

/**
 *
 * @author DELL
 */
public class EvenThread implements Runnable{
    public static void main(String[] args) {
        EvenThread e= new EvenThread();
        Thread t= new Thread(e);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if(i%2==0){
                System.out.println(""+i);
            }
            try {
                Thread.sleep(15);
            } catch (Exception e) {
                System.out.println(""+e);
            }
        }
    }
}
