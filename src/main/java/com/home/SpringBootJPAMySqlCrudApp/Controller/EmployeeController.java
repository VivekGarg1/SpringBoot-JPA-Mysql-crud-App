package com.home.SpringBootJPAMySqlCrudApp.Controller;

import com.home.SpringBootJPAMySqlCrudApp.Exception.ResourceNotFoundException;
import com.home.SpringBootJPAMySqlCrudApp.Model.Employee;
import com.home.SpringBootJPAMySqlCrudApp.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable(value="id") Long employeeId) throws ResourceNotFoundException {
        return employeeService.findEmployeeById(employeeId);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        return employeeService.updateEmployee(employeeId,employeeDetails);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean>  deleteEmployee(@PathVariable(value = "id") Long employeeId) throws  ResourceNotFoundException {
        return employeeService.deleteEmployee(employeeId);
    }

}
