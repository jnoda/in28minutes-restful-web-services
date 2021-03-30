package com.julionoda.in28minutes.rest.webservices.users;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer id;

    @NotBlank
    @Size(min = 2, message = "name must have at least {min} characters")
    private String name;

    @Past
    private LocalDate birthDate;
}
