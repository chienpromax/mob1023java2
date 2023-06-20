/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd08548_tranxuanchien_lab6;

/**
 *
 * @author DELL
 */
public class OddThread implements Runnable {
    public static void main(String[] args) {
        OddThread o = new OddThread();
        Thread t =new Thread(o);
        t.start();
    }
    @Override
    public void run() {
            for (int i = 0; i < 10; i++) {
                if(i % 2 == 1){
                    System.out.println("" + i);
                }
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    System.out.println(""+e);
                }
            }
    }

}
