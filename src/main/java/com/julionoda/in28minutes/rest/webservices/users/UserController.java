package com.julionoda.in28minutes.rest.webservices.users;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserController(UserRepository userRepository,
                          PostRepository postRepository) {

        this.userRepository = Objects.requireNonNull(userRepository);
        this.postRepository = Objects.requireNonNull(postRepository);
    }

    @GetMapping("/users")
    public List<User> findAll() {
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

    @GetMapping("/users/{id}/posts")
    public List<Post> findAll(@PathVariable Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id-" + id));

        return user.getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> save(@PathVariable Integer id, @Valid @RequestBody Post post) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id-" + id));

        post.setUser(user);
        Post createdPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{postId}")
                .buildAndExpand(createdPost.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(createdPost);
    }
}
