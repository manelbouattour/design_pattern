package db_singleton_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/modelisation";
    private static final String USER = "root";
    private static final String PASS = "";
    
    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connexion à la base de données établie avec succès.");

            // Fermer proprement la connexion à la fin du programme
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                        System.out.println("Connexion à la base de données fermée proprement.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }));
            
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
    
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        return connection;
    }
}
