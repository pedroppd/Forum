package br.com.forum.config.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
@AllArgsConstructor
public class ErrorDto {
    private HttpStatus status;
    private String field;
    private String message;

    public ErrorDto(){

    }

    public ErrorDto(HttpStatus status, String message){
        this.setStatus(status);
     this.setMessage(message);
    }

}
