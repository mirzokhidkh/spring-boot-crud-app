package uz.mk.springbootcrudapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.mk.springbootcrudapp.domain.Employee;
import uz.mk.springbootcrudapp.payload.Response;
import uz.mk.springbootcrudapp.payload.EmployeeDTO;
import uz.mk.springbootcrudapp.service.EmployeeService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Tag(name = "Employee", description = "Employee management APIs")
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
            summary = "Save Employee data",
            description = "Save Employee data ()",
            tags = "Employee"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "account successfully created case." +
                                    "",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)
                            )
                    )
            }
    )
    @PostMapping("/save")
    public HttpEntity<?> save(@Valid @Parameter(name = "Request body", required = true) @RequestBody EmployeeDTO employeeDTO) {

        Response response = employeeService.save(employeeDTO);
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
        Response response = employeeService.getOne(id);
        return ResponseEntity.status(response.isStatus() ? 200 : 404).body(response);
    }

    /**
     * @param employeeDTO
     * @param id
     */
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable Long id) {
        Response response = employeeService.edit(employeeDTO, id);
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
        Response response = employeeService.changeSalary(id, salary);
        return ResponseEntity.status(response.isStatus() ? 202 : 400).body(response);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        Response response = employeeService.delete(id);
        return ResponseEntity.ok(response);
    }


}
