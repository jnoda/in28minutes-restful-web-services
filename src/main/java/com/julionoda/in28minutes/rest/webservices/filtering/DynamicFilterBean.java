package com.julionoda.in28minutes.rest.webservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Value;

@Value
@JsonFilter("SimpleBeanFilter")
public class DynamicFilterBean {
    String property1;
    String property2;
    String property3;
}
