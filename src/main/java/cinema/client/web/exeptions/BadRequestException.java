package cinema.client.web.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST,reason="BAD REQUEST")
public class BadRequestException extends RuntimeException {
}
