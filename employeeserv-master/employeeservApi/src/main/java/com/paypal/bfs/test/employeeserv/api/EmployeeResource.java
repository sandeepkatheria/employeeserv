package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource {

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @RequestMapping(value = "/v1/bfs/employees/{id}",method = RequestMethod.GET)
    ResponseEntity<Employee> employeeGetById(@PathVariable("id") String id);

    // ----------------------------------------------------------
    // add a new operation for creating employee resource.
    // ----------------------------------------------------------

    /**
     * Retrieves the {@link Employee} resource employee.
     *
     * @param employee.
     * @return {@link Employee} resource.
     */
    @RequestMapping(value = "/v1/bfs/employees/create",method = RequestMethod.POST)
    ResponseEntity<Employee> createEmployee(@RequestBody Employee employee);
}
