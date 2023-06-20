/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd08548_tranxuanchien_java2ass2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author DELL
 */
public class clock implements Runnable{
    JLabel clock;

    public clock(JLabel clock) {
        this.clock = clock;
    }
    
    
    @Override
    public void run() {
        while (true) {
            
                Date now = new Date();
                SimpleDateFormat datefomat = new SimpleDateFormat("hh:mm:ss aa");
                clock.setText((datefomat.format(now)));
                try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(clock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
