package uz.mk.springbootcrudapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response {
    private String message;
    private boolean status;
    private Object object;

    public Response(String message, boolean status) {
        this.message = message;
        this.status = status;
    }
}
