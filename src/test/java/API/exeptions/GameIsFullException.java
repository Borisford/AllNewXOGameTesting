package API.exeptions;

public class GameIsFullException extends Exception {
    public GameIsFullException(String message) {
        super(message);
    }
}
