package com.julionoda.in28minutes.rest.webservices.hello;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Objects;

@RestController
public class HelloController {
    private final MessageSource messageSource;

    public HelloController(MessageSource messageSource) {
        this.messageSource = Objects.requireNonNull(messageSource);
    }

    @GetMapping("/greet")
    public String greet(Locale locale) {
        return messageSource.getMessage("good.morning.message", null, locale);
    }
}
