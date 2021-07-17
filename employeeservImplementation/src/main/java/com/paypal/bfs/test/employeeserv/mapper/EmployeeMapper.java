package com.paypal.bfs.test.employeeserv.mapper;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.persistence.EmployeeDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EmployeeMapper implements Function<EmployeeDto, Employee> {
    @Override
    public Employee apply(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());

        Address address = new Address();
        address.setLine1(employeeDto.getAddressLine1());
        address.setLine2(employeeDto.getAddressLine2());
        address.setCity(employeeDto.getCity());
        address.setState(employeeDto.getState());
        address.setCountry(employeeDto.getCountry());
        address.setZipCode(employeeDto.getZipCode());

        employee.setAddress(address);

        return employee;
    }
}
