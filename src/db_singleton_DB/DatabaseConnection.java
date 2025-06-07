package db_singleton_DB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
//Singleton pour la connexion à la base de données
	private static DatabaseConnection instance;
    private Connection connection;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/modelisation";
    private static final String USER = "root";
    private static final String PASS = "";
    
    private DatabaseConnection() {
        try {
          connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connexion à la base de données établie avec succès.");
        } catch (SQLException e) {
        		e.printStackTrace();       
      }
    }
    
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public Connection getConnection() {
        return connection;
    }
    
}
