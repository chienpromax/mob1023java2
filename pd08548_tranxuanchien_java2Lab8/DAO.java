package pd08548_tranxuanchien_java2Lab8;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

abstract public class DAO<Entity> {
    protected List<Entity> list = new ArrayList();
    
    public void add(Entity entity){
        list.add(entity);
    }
    
    public void remove(Entity entity){
        list.remove(entity);
    }
    
    abstract public void update(Entity entity);
    abstract public Entity find(Serializable id);
    
    public List<Entity> getList() {
        return list;
    }
    
    public void load(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            list = (List<Entity>) ois.readObject();
        }

    }
    
    public void store(String path) throws FileNotFoundException, IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(list);
        }
    }
}
