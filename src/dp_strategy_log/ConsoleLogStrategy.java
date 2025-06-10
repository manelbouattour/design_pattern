package dp_strategy_log;

import java.time.LocalDateTime;

public class ConsoleLogStrategy implements LogStrategy {
    @Override
    public void log(String message) {
        String logMessage = String.format("[%s] %s", LocalDateTime.now(), message);
        System.out.println(logMessage);
    }
}