package mob1023_lab05;

import java.io.Serializable;

public class Student implements Serializable{
    public String name;
    public double marks;
    public String major;

    public Student(String name, double marks, String major) {
        this.name = name;
        this.marks = marks;
        this.major = major;
    }
    
    public String getGrade(){
        if(this.marks > 9){
            return "Xuất sắc";
        } else if(this.marks > 7.5){
            return "Giỏi";
        } else if(this.marks > 6.5){
            return "Khá";
        } else if(this.marks > 5){
            return "Trung bình";
        } else if(this.marks > 3){
            return "Yếu";
        }
        
        return "Kém";
    }
    
    public boolean isBonus(){
        return this.marks >= 7.5;
    }
}
