package br.com.forum.config.validation.exception;

import br.com.forum.config.validation.exception.dto.ErrorDto;
import br.com.forum.config.validation.exception.dto.MethodArgumentNotValidExceptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
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
    public List<MethodArgumentNotValidExceptionDto> handler(MethodArgumentNotValidException exception){
        List<MethodArgumentNotValidExceptionDto> errorDtoList = new ArrayList<>();
        exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .forEach(e -> {
                    String errorMessage = messageSource.getMessage(e, LocaleContextHolder.getLocale());
                    errorDtoList.add(new MethodArgumentNotValidExceptionDto(400, e.getField(), errorMessage));
                });

        return errorDtoList;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorDto handler(IllegalArgumentException exception){
        return new ErrorDto(400, exception.getMessage());
    }
}