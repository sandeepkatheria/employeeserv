package com.paypal.bfs.test.employeeserv.repository;

import com.paypal.bfs.test.employeeserv.domain.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeResourceRepo extends JpaRepository<EmployeeEntity,Integer> {
    Optional<EmployeeEntity> findByEmployeeCode(Integer employeeCode);
}
