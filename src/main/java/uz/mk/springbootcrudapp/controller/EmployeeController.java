package uz.mk.springbootcrudapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.mk.springbootcrudapp.domain.Employee;
import uz.mk.springbootcrudapp.payload.ApiResponse;
import uz.mk.springbootcrudapp.payload.EmployeeDTO;
import uz.mk.springbootcrudapp.service.EmployeeService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/employee")
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * @param employeeDTO
     * @return
     */

    @Operation(
            summary = "Создание счета  (Физ)",
            description = "Создание счета  <b>(Физ)</b>",
            tags = "Account"
    )
    @ApiResponses(
            value = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                            description = "account successfully created case." +
                                    "",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class)
                            )
                    )
            }
    )
    @PostMapping
    public HttpEntity<?> save(@Valid @Parameter(name = "Request body", required = true) @RequestBody EmployeeDTO employeeDTO) {

        ApiResponse response = employeeService.save(employeeDTO);
        return ResponseEntity.status(response.isStatus() ? 201 : 400).body(response);
    }


    /**
     * @return
     */

    @GetMapping("/list")
    public Page<Employee> getAll(@NotNull @NotBlank @RequestParam String page,@RequestParam(required = false) String test) {

            Pageable pageable = PageRequest.of(0, 10);
//            Pageable pageable = PageRequest.of(page, 10);
        return employeeService.getAll(pageable);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@NotNull @PathVariable Long id) {
        ApiResponse response = employeeService.getOne(id);
        return ResponseEntity.status(response.isStatus() ? 200 : 404).body(response);
    }

    /**
     * @param employeeDTO
     * @param id
     */
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable Long id) {
        ApiResponse response = employeeService.edit(employeeDTO, id);
        return ResponseEntity.status(response.isStatus() ? 202 : 400).body(response);
    }

    /**
     * @param id
     * @param salary
     * @return
     */
    @PutMapping("/{id}/change-salary")
    public HttpEntity<?> changeSalary(@PathVariable Long id,
                                      @RequestParam Double salary) {
        ApiResponse response = employeeService.changeSalary(id, salary);
        return ResponseEntity.status(response.isStatus() ? 202 : 400).body(response);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse response = employeeService.delete(id);
        return ResponseEntity.ok(response);
    }


}
