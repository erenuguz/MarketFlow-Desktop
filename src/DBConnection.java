package kasiyerUygulamasi;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/kasiyer?useUnicode=true&characterEncoding=UTF-8";
            String user = "root";
            String password = ""; 
            
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Bağlantı başarısız: " + e.getMessage());
            return null;
        }
    }
}