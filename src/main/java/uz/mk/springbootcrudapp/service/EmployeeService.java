package uz.mk.springbootcrudapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.mk.springbootcrudapp.domain.Employee;
import uz.mk.springbootcrudapp.payload.ApiResponse;
import uz.mk.springbootcrudapp.payload.EmployeeDTO;

public interface EmployeeService {

    Page<Employee> getAll(Pageable pageable);

    ApiResponse getOne(Long id);

    ApiResponse save(EmployeeDTO employeeDTO);

    ApiResponse edit(EmployeeDTO employeeDTO, Long id);

    ApiResponse changeSalary(Long id, Double salary);

    ApiResponse delete(Long id);
}
