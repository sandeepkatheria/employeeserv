package com.paypal.bfs.test.employeeserv.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Entity
@Table(name="address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @NotNull(message = "Address line1 can not be blank")
    private String addressLine1;
    private String addressLine2;
    @NotNull(message = "City can not be blank")
    private String city;
    @NotNull(message = "State can not be blank")
    private String state;
    @NotNull(message = "Country can not be blank")
    private String country;
    @NotNull(message = "Zip code can not be blank")
    private String zipCode;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "emp_id")
    private EmployeeEntity employee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(Id, that.Id) && Objects.equals(addressLine1, that.addressLine1)
                && Objects.equals(addressLine2, that.addressLine2) && Objects.equals(city, that.city)
                && Objects.equals(state, that.state) && Objects.equals(country, that.country)
                && Objects.equals(zipCode, that.zipCode) && Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressLine1.toUpperCase(), city.toUpperCase(),
                state.toUpperCase(), country.toUpperCase(), zipCode.toUpperCase());
    }
}
