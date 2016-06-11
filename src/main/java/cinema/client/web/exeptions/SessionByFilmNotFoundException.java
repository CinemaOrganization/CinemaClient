package cinema.client.web.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND,
        reason="Spittle Not Found")
public class SessionByFilmNotFoundException extends HttpMessageNotReadableException {

    public SessionByFilmNotFoundException() {
        super("exception");
    }

    public SessionByFilmNotFoundException(String msg) {
        super(msg);
    }

    public SessionByFilmNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
