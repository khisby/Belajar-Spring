package id.khisoft.javaspring.Controller;

import id.khisoft.javaspring.Entity.Employee;
import id.khisoft.javaspring.Repository.EmployeeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    @Qualifier("EmployeeRepository")
    private EmployeeImpl employeeRepository;

    @GetMapping("/employee")
    public List<Employee> getEmployee(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employee")
    public int Postemployee(@Valid @RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/employee")
    public int deleteEmployee(@RequestParam("id") Long id){
        return employeeRepository.deleteById(id);
    }

    @PutMapping("/employee")
    public int updateEmployee(@Valid @RequestBody Employee employee){
        return employeeRepository.update(employee);
    }
}
