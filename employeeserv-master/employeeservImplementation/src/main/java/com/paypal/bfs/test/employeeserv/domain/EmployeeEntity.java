package com.paypal.bfs.test.employeeserv.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name="employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @NotNull(message = "date of birth can not be blank")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @NotNull(message = "First name can not be blank")
    private String firstName;

    @NotNull(message = "Last name can not be blank")
    private String lastName;

    private Integer employeeCode;

    @NotNull(message = "address can not be blank")
    @OneToOne(mappedBy = "employee", cascade=CascadeType.ALL)
    private AddressEntity address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(Id, that.Id) && Objects.equals(dateOfBirth, that.dateOfBirth)
                && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName)
                && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfBirth, firstName.toUpperCase(), lastName.toUpperCase(), address.hashCode());
    }

}
