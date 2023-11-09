package mariangelamarasciuolo.BlogDayThree.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorPayload handleBadRequest(BadRequestException e) {

        return new ErrorPayload(e.getMessage(), new Date());
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    public ErrorPayload handleNotFound(ChangeSetPersister.NotFoundException e) {
        return new ErrorPayload(e.getMessage(), new Date());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    public ErrorPayload handleGeneric(Exception e) {
        e.printStackTrace();
        return new ErrorPayload("Ha provato a spegnere e riaccendere...?", new Date());
    }
}
