package id.khisoft.javaspring.Repository;

import id.khisoft.javaspring.Entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    int count();
    int save(Employee employee);
    int update(Employee employee);
    int deleteById(Long id);

    List<Employee> findAll();
    List<Employee> findByLastName(String lastName);
    Optional<Employee> findById(Long id);
}
