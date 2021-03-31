package com.julionoda.in28minutes.rest.webservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Value;

@Value
public class StaticFilterBean {
    String property1;
    String property2;

    @JsonIgnore
    String property3;
}
