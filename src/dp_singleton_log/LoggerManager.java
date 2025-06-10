package dp_singleton_log;

import dp_strategy_log.FileLogStrategy;
import dp_strategy_log.LogStrategy;

public enum LoggerManager {
    INSTANCE;

    private LogStrategy currentStrategy = new FileLogStrategy
    		();

    public void setStrategy(LogStrategy strategy) {
        this.currentStrategy = strategy;
    }

    public void log(String message) {
        currentStrategy.log(message);
    }
}