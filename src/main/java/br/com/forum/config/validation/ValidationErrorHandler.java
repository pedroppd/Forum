package br.com.forum.config.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorDto> handler(MethodArgumentNotValidException exception){
        List<ErrorDto> errorDtoList = new ArrayList<>();
        exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .forEach(e -> {
                    String errorMessage = messageSource.getMessage(e, LocaleContextHolder.getLocale());
                    errorDtoList.add( new ErrorDto(HttpStatus.BAD_REQUEST, e.getField(), errorMessage));
                });

        return errorDtoList;
    }


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorDto handler(IllegalArgumentException exception){
        return new ErrorDto(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}
