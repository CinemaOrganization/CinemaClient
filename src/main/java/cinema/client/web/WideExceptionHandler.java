package cinema.client.web;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WideExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMyException(Exception  exception) {
        return "badrequest";
    }
}
