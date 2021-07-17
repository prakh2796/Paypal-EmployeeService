package com.paypal.bfs.test.employeeserv.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeDto, Integer> {
    Optional<EmployeeDto> findEmployeeByHashCode(int hash);
}
