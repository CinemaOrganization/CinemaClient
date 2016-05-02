package cinema.client.web;

import cinema.client.web.exeptions.BadRequestException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WideExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public String duplicateSpittleHandler() {
        return "badrequest";
    }
}
