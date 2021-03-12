package com.paypal.bfs.test.employeeserv.mapper;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.domain.AddressEntity;
import com.paypal.bfs.test.employeeserv.domain.EmployeeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@Component
public class RequestResponseMapper {

    public Employee convertDomainToResponse(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();
        employee.setId(employeeEntity.getId());
        employee.setFirstName(employeeEntity.getFirstName());
        employee.setLastName(employeeEntity.getLastName());
        if(employeeEntity.getAddress() !=null){
            Address address = new Address();
            address.setAddressLine1(employeeEntity.getAddress().getAddressLine1());
            address.setAddressLine2(employeeEntity.getAddress().getAddressLine2());
            address.setCity(employeeEntity.getAddress().getCity());
            address.setState(employeeEntity.getAddress().getState());
            address.setCountry(employeeEntity.getAddress().getCountry());
            address.setZipCode(employeeEntity.getAddress().getZipCode());
            employee.setAddress(address);
        }
        return employee;
    }


    public EmployeeEntity convertResponseToDomain(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setDateOfBirth(getDOB(employee.getDateOfBirth()));
        if(employee.getAddress() !=null){
            AddressEntity address = new AddressEntity();
            address.setAddressLine1(employee.getAddress().getAddressLine1());
            address.setAddressLine2(employee.getAddress().getAddressLine2());
            address.setCity(employee.getAddress().getCity());
            address.setState(employee.getAddress().getState());
            address.setCountry(employee.getAddress().getCountry());
            address.setZipCode(employee.getAddress().getZipCode());
            address.setEmployee(employeeEntity);
            employeeEntity.setAddress(address);
        }
        return employeeEntity;
    }

    private Date getDOB(String dob){
        try {
            ZoneId defaultZoneId = ZoneId.systemDefault();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(dob,formatter);
            return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        }catch (Exception ex){
            log.error("Error in getDOB",ex);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect date format, please enter date in format yyyy-MM-dd");
        }

    }

    public boolean isNum(String id){
        try{
            int idInt = Integer.parseInt(id.trim());
        }catch (NumberFormatException exception){
            return  false;
        }
        return true;
    }
}
