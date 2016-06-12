package cinema.client.web.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND,
        reason="Spittle Not Found")
public class SessionByFilmAndDateNotFoundException extends HttpMessageNotReadableException {

    public SessionByFilmAndDateNotFoundException() {
        super("exception");
    }

    public SessionByFilmAndDateNotFoundException(String msg) {
        super(msg);
    }

    public SessionByFilmAndDateNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
