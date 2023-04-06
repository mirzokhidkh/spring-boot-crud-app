package uz.mk.springbootcrudapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.mk.springbootcrudapp.domain.Employee;
import uz.mk.springbootcrudapp.payload.Response;
import uz.mk.springbootcrudapp.payload.EmployeeDTO;

public interface EmployeeService {

    Page<Employee> getAll(Pageable pageable);

    Response getOne(Long id);

    Response save(EmployeeDTO employeeDTO);

    Response edit(EmployeeDTO employeeDTO, Long id);

    Response changeSalary(Long id, Double salary);

    Response delete(Long id);
}
