package cinema.client.secure.exception;

public class UsernameExistException extends ExistsException {

    public UsernameExistException() {
    }

    public UsernameExistException(String message) {
        super(message);
    }
}
