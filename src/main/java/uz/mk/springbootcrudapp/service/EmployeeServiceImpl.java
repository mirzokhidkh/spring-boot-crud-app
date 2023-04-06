package uz.mk.springbootcrudapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.mk.springbootcrudapp.domain.Employee;
import uz.mk.springbootcrudapp.exception.ApiResourceNotFoundException;
import uz.mk.springbootcrudapp.payload.Response;
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
    public Response save(EmployeeDTO employeeDTO) {
        boolean existsByEmail = employeeRepository.existsByEmail(employeeDTO.getEmail());
        if (existsByEmail) {
            return new Response("Email '" + employeeDTO.getEmail() + "' already exists", false);
        }

        Employee employee = new Employee();
        mapEmployeeDtoToEmployee(employeeDTO, employee);

        Employee savedEmployee = employeeRepository.save(employee);

        return new Response("Employee saved", true, savedEmployee);
    }


    @Override
    public Page<Employee> getAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }


    @Override
    public Response getOne(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.map(employee -> new Response("Employee", true, employee))
                .orElseThrow(() -> new ApiResourceNotFoundException("Employee ID '" + id + "' does not found"));
    }


    @Override
    public Response edit(EmployeeDTO employeeDTO, Long id) {
        Employee editingEmployee = employeeRepository.findById(id).orElse(null);
        if (editingEmployee == null) {
            throw new ApiResourceNotFoundException("Employee ID '" + id + "' does not found");
        }

        boolean existsByEmailAndIdNot = employeeRepository.existsByEmailAndIdNot(employeeDTO.getEmail(), id);
        if (existsByEmailAndIdNot) {
            return new Response("Employee with such a email '" + employeeDTO.getEmail() + "'  already exists", false);
        }

        mapEmployeeDtoToEmployee(employeeDTO, editingEmployee);

        Employee editedEmployee = employeeRepository.save(editingEmployee);

        return new Response("Employee edited", true, editedEmployee);
    }

    @Override
    public Response changeSalary(Long id, Double salary) {
        if (salary < 100) {
            return new Response("Salary must be at least 100.0", false);
        }
        employeeRepository.changeSalary(id, salary);
        return new Response("Employee' salary changed", true);
    }

    @Override
    public Response delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ApiResourceNotFoundException("Employee ID '" + id + "' does not found");
        }
        employeeRepository.deleteById(id);
        return new Response("Employee deleted", true);
    }


    private void mapEmployeeDtoToEmployee(EmployeeDTO employeeDTO, Employee employee) {
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());
        employee.setAddress(employeeDTO.getAddress());
    }

}
