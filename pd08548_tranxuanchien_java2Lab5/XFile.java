package mob1023_lab05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class XFile {
    public byte[] readFile(String path) throws FileNotFoundException, IOException{
        byte[] data;
        try (FileInputStream fis = new FileInputStream(path)) {
            int n = fis.available();
            data = new byte[n];
            fis.read(data);
        }
        return data;
    }
    
    public void writeFile(String path, byte[] data) throws FileNotFoundException, IOException{
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(data);
        }
    }
    
    public static Object readObject(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        Object object;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            object = ois.readObject();
        }
        return object;
    }
    
    public static void writeObject(String path, Object object) throws FileNotFoundException, IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(object);
        }
    }
}
