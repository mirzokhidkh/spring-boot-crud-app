package uz.mk.springbootcrudapp.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class EmployeeDTO {
    @Schema(defaultValue = "John Doe")
    @NotNull(message = "name must not be empty")
    @Size(min = 2)
    private String name;
    @Schema(defaultValue = "john@gmail.com")
    @NotBlank(message = "email is required")
    @Email(message = "This needs to be an email")
    private String email;

    @Schema(defaultValue = "IT")
    @NotBlank(message = "department is required")
    private String department;
    @Schema(defaultValue = "100000")
    @Min(value = 100, message = "Salary must be at least 100.0")
    private Double salary;

    @Schema(defaultValue = "Bla bla")
    @NotBlank(message = "address is required")
    private String address;

//    @Max(2)
////    @JsonFormat(shape = JsonFormat.Shape.STRING)
//    private Integer numb;

//    @Digits(integer = 20, fraction = 5)
//    @JsonFormat(shape = JsonFormat.Shape.STRING)
//    private BigDecimal summa;
}
