package com.paypal.bfs.test.employeeserv.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import javax.validation.Validation;
import javax.validation.Validator;

@Component
public class EmployeeValidator{

    private SpringValidatorAdapter validatorAdapter;

    @PostConstruct
    public void init(){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        validatorAdapter = new SpringValidatorAdapter(validator);
    }

    public Object validate(Object entity){
                Errors errors =  new BeanPropertyBindingResult(entity, entity.getClass().getName());
                validatorAdapter.validate(entity,errors);
                if(errors !=null && !errors.getAllErrors().isEmpty()){
                    onValidationErrors(errors);
                }
                return entity;
    }

    private void onValidationErrors(Errors errors) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getAllErrors()
                .toString());
    }
}
