package mob1023_lab03;

import java.util.ArrayList;
import java.util.Collections;

public class StudentList {
    ArrayList<Student> stl = new ArrayList();
    
    public void them(Student st){
        stl.add(st);
    }
    
    public void sortByScore(){
        Collections.sort(stl, (Student st1, Student st2) -> Double.compare(st1.getScore(), st2.getScore()));
        Collections.reverse(stl);
    }
    
    public void sortByName(){
        Collections.sort(stl, (Student st1, Student st2) -> st1.getName().compareTo(st2.getName()));
    }
    
    public boolean check(){
        return stl.isEmpty();
    }
    
    public int len(){
        return stl.size() - 1;
    }
}
