package com.julionoda.in28minutes.rest.webservices.filtering;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Value;

@Value
public class ViewFilterBean {
    @JsonView(FilteredView.class)
    String property1;

    @JsonView(FilteredView.class)
    String property2;

    String property3;
}
