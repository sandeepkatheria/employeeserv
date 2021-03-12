package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.service.EmployeeResourceService;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Autowired
    private EmployeeResourceService employeeResourceService;


    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {
        return new ResponseEntity<>(employeeResourceService.findEmployeeById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Employee employee) {
        return new ResponseEntity<>(employeeResourceService.createEmployee(employee), HttpStatus.OK);
    }
}
