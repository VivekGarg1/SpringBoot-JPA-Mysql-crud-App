package com.home.SpringBootJPAMySqlCrudApp.Service;

import com.home.SpringBootJPAMySqlCrudApp.Dao.EmployeeDao;
import com.home.SpringBootJPAMySqlCrudApp.Exception.ResourceNotFoundException;
import com.home.SpringBootJPAMySqlCrudApp.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    public List<Employee> getAllEmployee(){
        return employeeDao.findAll();
    }

    public ResponseEntity<Employee> findEmployeeById(Long employeeId) throws ResourceNotFoundException {
          Employee employee = employeeDao.findById(employeeId).
                  orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    public Employee createEmployee(Employee employee){
        return employeeDao.save(employee);
    }

    public ResponseEntity<Employee> updateEmployee(Long employeeId, Employee employeeData) throws ResourceNotFoundException {
        Employee employee=employeeDao.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employee.setFirstName(employeeData.getFirstName());
        employee.setLastName(employeeData.getLastName());
        employee.setEmailId(employeeData.getEmailId());
        Employee updatedEmployee = employeeDao.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    public Map<String, Boolean> deleteEmployee(Long employeeId) throws  ResourceNotFoundException {
        Employee employee=employeeDao.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employeeDao.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
