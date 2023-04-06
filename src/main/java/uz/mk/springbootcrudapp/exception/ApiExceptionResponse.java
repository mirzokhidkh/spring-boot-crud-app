package uz.mk.springbootcrudapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiExceptionResponse {
    private String message;
}
