package API.exeptions;

public class BadNumberOfPlayersException extends Exception {
    public BadNumberOfPlayersException(String message) {
        super(message);
    }
}
