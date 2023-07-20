package cacttus.simplesocialmedia.controllers;

import cacttus.simplesocialmedia.dto.PostDto;
import cacttus.simplesocialmedia.model.Post;
import cacttus.simplesocialmedia.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@CrossOrigin

public class PostController {
    private final PostService postService;

    @PostMapping("/")
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto) throws Exception {
        Post post = postService.createPost(postDto);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

}
