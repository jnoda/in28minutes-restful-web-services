package com.julionoda.in28minutes.rest.webservices.users;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "User", description = "Stores a user.")
public class User {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer id;

    @NotBlank
    @Size(min = 2, message = "name must have at least {min} characters")
    private String name;

    @Past
    @Schema(description = "Birth date. Should be on the past.")
    private LocalDate birthDate;
}
