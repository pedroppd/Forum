package br.com.forum.config.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class IllegalArgumentExceptionDto {
    private int status;
    private String message;
}
