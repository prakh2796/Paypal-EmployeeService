package com.paypal.bfs.test.employeeserv.mapper;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.persistence.EmployeeDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EmployeeDtoMapper implements Function<Employee, EmployeeDto> {
    @Override
    public EmployeeDto apply(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setAddressLine1(employee.getAddress().getLine1());
        employeeDto.setAddressLine2(employee.getAddress().getLine2());
        employeeDto.setCity(employee.getAddress().getCity());
        employeeDto.setState(employee.getAddress().getState());
        employeeDto.setCountry(employee.getAddress().getCountry());
        employeeDto.setZipCode(employee.getAddress().getZipCode());

        //create hash after adding all other info
        employeeDto.setHashCode(employeeDto.generateHash());

        return employeeDto;
    }
}
