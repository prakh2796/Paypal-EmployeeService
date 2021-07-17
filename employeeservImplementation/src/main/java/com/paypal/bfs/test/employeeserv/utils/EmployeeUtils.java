package com.paypal.bfs.test.employeeserv.utils;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.EmployeeCrudException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class EmployeeUtils {
    public void validateGetEmployeeRequest(String id) {
        if(id == null || id.isEmpty())
            throw new EmployeeCrudException("Invalid employee id", HttpStatus.BAD_REQUEST.value(), "failure", null, null, null);

        try{
            Integer.valueOf(id);
        }catch (Exception exception){
            throw new EmployeeCrudException("Invalid employee id", HttpStatus.BAD_REQUEST.value(), "failure", null, null, null);
        }
    }

    public void validateCreateRequest(Employee employee) {
        if(employee == null)
            throw new EmployeeCrudException("Invalid payload", HttpStatus.BAD_REQUEST.value(), "failure", null, null, null);

        if(employee.getFirstName() == null || employee.getFirstName().isEmpty())
            throw new EmployeeCrudException("First name is missing", HttpStatus.BAD_REQUEST.value(), "failure", null, null, null);

        if(employee.getLastName() == null || employee.getLastName().isEmpty())
            throw new EmployeeCrudException("Last name is missing", HttpStatus.BAD_REQUEST.value(), "failure", null, null, null);

        if(employee.getDateOfBirth() == null)
            throw new EmployeeCrudException("Date of birth is missing", HttpStatus.BAD_REQUEST.value(), "failure", null, null, null);

        if(employee.getAddress() == null)
            throw new EmployeeCrudException("Address is missing", HttpStatus.BAD_REQUEST.value(), "failure", null, null, null);

        if(employee.getAddress().getLine1() == null || employee.getAddress().getLine1().isEmpty())
            throw new EmployeeCrudException("Address is missing", HttpStatus.BAD_REQUEST.value(), "failure", null, null, null);

        if(employee.getAddress().getCity() == null || employee.getAddress().getCity().isEmpty())
            throw new EmployeeCrudException("City is missing", HttpStatus.BAD_REQUEST.value(), "failure", null, null, null);

        if(employee.getAddress().getState() == null || employee.getAddress().getState().isEmpty())
            throw new EmployeeCrudException("State is missing", HttpStatus.BAD_REQUEST.value(), "failure", null, null, null);

        if(employee.getAddress().getCountry() == null || employee.getAddress().getCountry().isEmpty())
            throw new EmployeeCrudException("Country is missing", HttpStatus.BAD_REQUEST.value(), "failure", null, null, null);

        if(employee.getAddress().getZipCode() == null)
            throw new EmployeeCrudException("Zipcode is missing", HttpStatus.BAD_REQUEST.value(), "failure", null, null, null);
    }
}
