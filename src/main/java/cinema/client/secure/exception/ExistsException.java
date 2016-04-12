package cinema.client.secure.exception;

abstract public class ExistsException extends RuntimeException {

    public ExistsException() {
    }

    public ExistsException(String message) {
        super(message);
    }
}
