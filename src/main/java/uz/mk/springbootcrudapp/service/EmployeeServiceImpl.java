package uz.mk.springbootcrudapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.mk.springbootcrudapp.domain.Employee;
import uz.mk.springbootcrudapp.exception.ApiResourceNotFoundException;
import uz.mk.springbootcrudapp.payload.ApiResponse;
import uz.mk.springbootcrudapp.payload.EmployeeDTO;
import uz.mk.springbootcrudapp.repository.EmployeeRepository;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ApiResponse save(EmployeeDTO employeeDTO) {
        boolean existsByEmail = employeeRepository.existsByEmail(employeeDTO.getEmail());
        if (existsByEmail) {
            return new ApiResponse("Email '" + employeeDTO.getEmail() + "' already exists", false);
        }

        Employee employee = new Employee();
        mapEmployeeDtoToEmployee(employeeDTO, employee);

        Employee savedEmployee = employeeRepository.save(employee);

        return new ApiResponse("Employee saved", true, savedEmployee);
    }


    @Override
    public Page<Employee> getAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }


    @Override
    public ApiResponse getOne(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.map(employee -> new ApiResponse("Employee", true, employee))
                .orElseThrow(() -> new ApiResourceNotFoundException("Employee ID '" + id + "' does not found"));
    }


    @Override
    public ApiResponse edit(EmployeeDTO employeeDTO, Long id) {
        Employee editingEmployee = employeeRepository.findById(id).orElse(null);
        if (editingEmployee == null) {
            throw new ApiResourceNotFoundException("Employee ID '" + id + "' does not found");
        }

        boolean existsByEmailAndIdNot = employeeRepository.existsByEmailAndIdNot(employeeDTO.getEmail(), id);
        if (existsByEmailAndIdNot) {
            return new ApiResponse("Employee with such a email '" + employeeDTO.getEmail() + "'  already exists", false);
        }

        mapEmployeeDtoToEmployee(employeeDTO, editingEmployee);

        Employee editedEmployee = employeeRepository.save(editingEmployee);

        return new ApiResponse("Employee edited", true, editedEmployee);
    }

    @Override
    public ApiResponse changeSalary(Long id, Double salary) {
        if (salary < 100) {
            return new ApiResponse("Salary must be at least 100.0", false);
        }
        employeeRepository.changeSalary(id, salary);
        return new ApiResponse("Employee' salary changed", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ApiResourceNotFoundException("Employee ID '" + id + "' does not found");
        }
        employeeRepository.deleteById(id);
        return new ApiResponse("Employee deleted", true);
    }


    private void mapEmployeeDtoToEmployee(EmployeeDTO employeeDTO, Employee employee) {
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());
        employee.setAddress(employeeDTO.getAddress());
    }

}
