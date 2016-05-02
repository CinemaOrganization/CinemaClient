package cinema.client.secure.exception;

public class EmailExistsException extends ExistsException {

    public EmailExistsException() {
    }

    public EmailExistsException(String message) {
        super(message);
    }

}
