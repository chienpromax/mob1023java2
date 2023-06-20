package mob1023_lab03;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo {

    public static void main(String[] args) {
        Map<String, Student> map = new HashMap<>();
        
        Student st1 = new Student("Chung Tuấn", 10.0, "Thiết kế đồ họa", "Xuất sắc", true);
        Student st2 = new Student("Hoàng Lâm", 7.0, "Thiết kế đồ họa", "Khá", false);
        Student st3 = new Student("Minh Tuấn", 5.0, "Thiết kế đồ họa", "Trung bình", false);
        
        map.put(st1.getName(), st1);
        map.put(st2.getName(), st2);
        map.put(st3.getName(), st3);
        
        Set<String> keys = map.keySet();
        
        for(String name : keys){
            Student st = map.get(name);
            System.out.println("> Họ tên: " + st.getName());
            System.out.println("> Điểm số: " + String.format("%.1f", st.getScore()));
        }
    }
}
