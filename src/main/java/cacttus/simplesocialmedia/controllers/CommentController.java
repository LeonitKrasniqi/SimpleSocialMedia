package cacttus.simplesocialmedia.controllers;

import cacttus.simplesocialmedia.dto.CommentDto;
import cacttus.simplesocialmedia.model.Comment;
import cacttus.simplesocialmedia.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor

public class CommentController {
    private final CommentService commentService;

    @PostMapping("/")
    public ResponseEntity<Comment> makeComment(@RequestBody CommentDto commentDto) throws Exception {
       Comment comment = commentService.makeComment(commentDto);
        return ResponseEntity.ok(comment);
    }
}
