package pd08548_tranxuanchien_java2Lab8;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductManager {

    public static void main(String[] args) {
        Product p1 = new Product("iPhone15", 1000.0);
        Product p2 = new Product("Samsung Start", 3000.0);
        ProductDAO dao = new ProductDAO() {};
        dao.add(p1);
        dao.add(p2);

        try {
            dao.store("prod.dat");
        } catch (IOException ex) {
            Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ProductDAO dao2 = new ProductDAO() {};
        try {
            dao2.load("prod.dat");
            Product p = dao2.find("iPhone15");
            System.out.println("> Name: " + p.getName());
            System.out.println("> Price: " + p.getPrice());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}
