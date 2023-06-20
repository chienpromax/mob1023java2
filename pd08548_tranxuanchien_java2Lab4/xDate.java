/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd08548_tranxuanchien_java2Lab4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class xDate {
    private  static SimpleDateFormat format = new SimpleDateFormat();   
    public static Date parse(String text,String pattern) throws ParseException{
        format.applyPattern(pattern);
        return  format.parse(text);
    }
    public static Date parse(String text) throws ParseException{
        return xDate.parse(text,"dd-mm-yyyy");
        
    }
}
