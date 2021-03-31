package com.julionoda.in28minutes.rest.webservices.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Post {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer id;

    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
}
