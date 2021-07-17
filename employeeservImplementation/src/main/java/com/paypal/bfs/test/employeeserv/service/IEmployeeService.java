package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

public interface IEmployeeService {
    Employee getEmployee(String id);
    Integer createEmployee(Employee employee);
}
