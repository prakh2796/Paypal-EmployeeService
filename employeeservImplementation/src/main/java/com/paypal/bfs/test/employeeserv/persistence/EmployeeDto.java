package com.paypal.bfs.test.employeeserv.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "employee")
@Table(name = "employee")
@Data
public class EmployeeDto {
    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Integer id;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private Integer zipCode;
    private int hashCode;

    public EmployeeDto() {
    }

    public EmployeeDto(String firstName, String lastName, Date dateOfBirth, String addressLine1, String addressLine2, String city, String state, String country, Integer zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public int generateHash(){
        String firstName = this.firstName == null ? "" : this.firstName;
        String lastName = this.lastName == null ? "" : this.lastName;
        String dateOfBirth = this.dateOfBirth == null ? "" : String.valueOf(this.dateOfBirth);
        String line1 = this.addressLine1 == null ? "" : this.addressLine1;
        String line2 = this.addressLine2 == null ? "" : this.addressLine2;
        String city = this.city == null ? "" : this.city;
        String state = this.state == null ? "" : this.state;
        String country = this.country == null ? "" : this.country;
        String zipCode = this.zipCode == null ? "" : String.valueOf(this.zipCode);

        String hash = firstName +
                lastName +
                dateOfBirth +
                line1 +
                line2 +
                city +
                state +
                country +
                zipCode;

        return hash.hashCode();
    }
}
