package br.com.forum.config.validation.exception.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MethodArgumentNotValidExceptionDto extends ErrorDto{
    private String field;

    public MethodArgumentNotValidExceptionDto(int status, String field, String message) {
        super(status, message);
        this.setField(field);
    }
}
