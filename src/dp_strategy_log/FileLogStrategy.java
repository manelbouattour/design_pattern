package dp_strategy_log;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class FileLogStrategy implements LogStrategy {
    private static final String LOG_PATH = "logs.txt"; 

    @Override
    public void log(String message ){
        String entry = String.format("[%s] %s\n", LocalDateTime.now(), message); 

        try {
            Files.write(
                Paths.get(LOG_PATH),
                entry.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.err.println("Erreur d'écriture dans logs.txt: " + e.getMessage());
        }
    }
}