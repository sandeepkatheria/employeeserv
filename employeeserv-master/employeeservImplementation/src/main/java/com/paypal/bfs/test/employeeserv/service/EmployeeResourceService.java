package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.domain.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.mapper.RequestResponseMapper;
import com.paypal.bfs.test.employeeserv.repository.EmployeeResourceRepo;
import com.paypal.bfs.test.employeeserv.validator.EmployeeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;


/**
 * Implementation class for EmployeeResourceService.
 */
@Slf4j
@Service
public class EmployeeResourceService {
    @Autowired
    private EmployeeResourceRepo employeeResourceRepo;
    @Autowired
    private RequestResponseMapper requestResponseMapper;
    @Autowired
    private EmployeeValidator employeeValidator;

    public Employee findEmployeeById(String id){
        if(!requestResponseMapper.isNum(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Incorrect id");
        }
        Optional<EmployeeEntity> employeeEntity = employeeResourceRepo.findById(Integer.valueOf(id.trim()));
        if(!employeeEntity.isPresent()){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No record found");
        }
        return requestResponseMapper.convertDomainToResponse(employeeEntity.get());
     }

    /**
     *  createEmployee method is used to create employee
     * @param employee
     * @return
     */
    public Employee createEmployee(final Employee employee){
        Employee employeeEntityResponse = employee;
        EmployeeEntity employeeEntity = requestResponseMapper.convertResponseToDomain(employee);
        // validate employee request object
        employeeValidator.validate(employeeEntity);
        // validating address request object
        employeeValidator.validate(employeeEntity.getAddress());
        //verifying if record exists
        checkRecordIsPresentInDb(employeeEntity);
        try {
            // persist data into db
            saveEmployee(employeeEntity);
            employeeEntityResponse.setId(employeeEntity.getId());
        }catch(Exception ex){
            log.error(" Error in createEmployee: ",ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"no record has been saved");
        }
        return employeeEntityResponse;
    }

    /**
     * saveEmployee persisting data into db
     * @param employeeEntity
     */
    @Transactional
    private void saveEmployee(EmployeeEntity employeeEntity) {
        employeeEntity.setEmployeeCode(employeeEntity.hashCode());
        employeeResourceRepo.save(employeeEntity);
    }

    /**
     * checkRecordIsPresentInDb checks if data exists in db
     * @param employeeEntity
     */
    private void checkRecordIsPresentInDb(EmployeeEntity employeeEntity){
        Optional<EmployeeEntity> entityOptional = Optional.empty();
        boolean findById = employeeEntity.getId() !=null?Boolean.TRUE:Boolean.FALSE;
        if(findById)
            entityOptional = employeeResourceRepo.findById(employeeEntity.getId());
        else
            entityOptional = employeeResourceRepo.findByEmployeeCode(employeeEntity.hashCode());

        if(entityOptional.isPresent() || findById){
            throw new ResponseStatusException(HttpStatus.FOUND,"Record exists already");
        }
    }
}
