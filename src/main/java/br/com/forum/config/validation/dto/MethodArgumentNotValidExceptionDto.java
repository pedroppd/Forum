package br.com.forum.config.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class MethodArgumentNotValidExceptionDto {
    private int status;
    private String field;
    private String message;
}
