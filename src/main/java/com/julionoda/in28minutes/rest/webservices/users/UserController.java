package com.julionoda.in28minutes.rest.webservices.users;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @GetMapping("/users")
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> findById(@PathVariable Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id-" + id));

        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id-" + id));

        userRepository.delete(user);
    }

    @PostMapping("/users")
    public ResponseEntity<User> save(@Valid @RequestBody User user) {
        User createdUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(createdUser);
    }
}
