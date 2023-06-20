package mob1023_lab05;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Phan1 {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        List<Student> list = new ArrayList();
        list.add(new Student("Tuấn", 5, "CNTT"));
        list.add(new Student("Cường", 7.5, "TKTW"));
        list.add(new Student("Hạnh", 8.5, "CNTT"));
        
        XFile.writeObject("student.dat", list);
        
        List<Student> list2 = (List<Student>) XFile.readObject("student.dat");
        for(Student sv : list2){
            System.out.println("> Họ và tên: " + sv.name);
        }
    }
    
}
