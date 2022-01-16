package br.com.forum.config.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ErrorDto {
    private String status;
    private String field;
    private String message;

    public ErrorDto(){

    }

}
