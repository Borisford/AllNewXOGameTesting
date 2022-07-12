package API.exeptions;

public class NoPlayerInGameException extends Exception {
    public NoPlayerInGameException(String message) {
        super(message);
    }
}
