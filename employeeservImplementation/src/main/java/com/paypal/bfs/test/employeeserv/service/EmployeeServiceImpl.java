package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.EmployeeCrudException;
import com.paypal.bfs.test.employeeserv.mapper.EmployeeDtoMapper;
import com.paypal.bfs.test.employeeserv.mapper.EmployeeMapper;
import com.paypal.bfs.test.employeeserv.persistence.EmployeeDto;
import com.paypal.bfs.test.employeeserv.persistence.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.utils.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;
    private EmployeeDtoMapper employeeDtoMapper;
    private EmployeeUtils employeeUtils;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, EmployeeDtoMapper employeeDtoMapper, EmployeeUtils employeeUtils) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.employeeDtoMapper = employeeDtoMapper;
        this.employeeUtils = employeeUtils;
    }

    @Override
    public Employee getEmployee(String id) {
        employeeUtils.validateGetEmployeeRequest(id);
        Optional<EmployeeDto> employeeDtoOptional = employeeRepository.findById(Integer.valueOf(id));
        if(!employeeDtoOptional.isPresent())
            throw  new EmployeeCrudException("Employee doesn't exist with id: " + id, HttpStatus.NOT_FOUND.value(), "failure", null, null, null);

        EmployeeDto employeeDto = employeeDtoOptional.get();
        return employeeMapper.apply(employeeDto);
    }

    @Override
    public Integer createEmployee(Employee employee) {
        employeeUtils.validateCreateRequest(employee);
        EmployeeDto employeeDto = employeeDtoMapper.apply(employee);
        Optional<EmployeeDto> employeeByHashCode = employeeRepository.findEmployeeByHashCode(employeeDto.getHashCode());
        if(employeeByHashCode.isPresent())
            throw new EmployeeCrudException("Employee already exist", HttpStatus.CONFLICT.value(), "failure", null, null, null);
        EmployeeDto createdEmployee = employeeRepository.save(employeeDto);
        return createdEmployee.getId();
    }
}
