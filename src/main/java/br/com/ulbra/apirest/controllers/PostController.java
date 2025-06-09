package br.com.ulbra.apirest.controllers;

import br.com.ulbra.apirest.dto.posts.request.PostRequest;
import br.com.ulbra.apirest.dto.posts.response.PostResponseDTO;
import br.com.ulbra.apirest.entities.Post;
import br.com.ulbra.apirest.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponseDTO> getAllPosts() {
        return this.postService.getAllPosts();
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest) {
        Post post = this.postService.createPost(postRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(uri).body(post);
    }
}
