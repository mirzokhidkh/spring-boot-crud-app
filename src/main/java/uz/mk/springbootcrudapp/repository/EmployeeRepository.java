package uz.mk.springbootcrudapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.mk.springbootcrudapp.domain.Employee;

import javax.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsById(Long id);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    @Transactional
    @Modifying
    @Query(value = "update employee set salary= :salary where id= :id", nativeQuery = true)
    void changeSalary(@Param("id") Long id,
                      @Param("salary") Double salary);
}
