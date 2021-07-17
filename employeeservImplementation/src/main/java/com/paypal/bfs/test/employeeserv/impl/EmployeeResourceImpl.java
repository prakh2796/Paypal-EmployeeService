package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.EmployeeCrudException;
import com.paypal.bfs.test.employeeserv.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    private IEmployeeService employeeService;

    @Autowired
    public EmployeeResourceImpl(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {

        try {
            return new ResponseEntity<Employee>(employeeService.getEmployee(id), HttpStatus.OK);
        } catch (EmployeeCrudException e) {
            Map<String, String> additionalInfo = new HashMap<>();
            additionalInfo.put("status", e.getStatusText());
            additionalInfo.put("reason", e.getMessage());
            return new ResponseEntity(additionalInfo, HttpStatus.valueOf(e.getRawStatusCode()));
        }
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Employee employee) {
        try {
            Integer employeeId = employeeService.createEmployee(employee);
            employee.setId(employeeId);
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        } catch (EmployeeCrudException e) {
            Map<String, String> additionalInfo = new HashMap<>();
            additionalInfo.put("status", e.getStatusText());
            additionalInfo.put("reason", e.getMessage());
            return new ResponseEntity(additionalInfo, HttpStatus.valueOf(e.getRawStatusCode()));
        }
    }
}
