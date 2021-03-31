package com.julionoda.in28minutes.rest.webservices.filtering;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
    @GetMapping("/filtering-static")
    public StaticFilterBean filteringStatic() {
        return new StaticFilterBean("value1", "value2", "value3");
    }

    @GetMapping("/filtering-dynamic")
    public MappingJacksonValue filteringDynamic() {
        DynamicFilterBean bean = new DynamicFilterBean("value1", "value2", "value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("property1", "property2");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("SimpleBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(bean);
        mapping.setFilters(filterProvider);

        return mapping;
    }

    @JsonView(FilteredView.class)
    @GetMapping("/filtering-view")
    public ViewFilterBean filteringView() {
        return new ViewFilterBean("value1", "value2", "value3");
    }
}
