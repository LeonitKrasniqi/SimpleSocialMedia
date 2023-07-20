package cacttus.simplesocialmedia.service;

import cacttus.simplesocialmedia.dto.CommentDto;
import cacttus.simplesocialmedia.model.Comment;
import cacttus.simplesocialmedia.model.Post;
import cacttus.simplesocialmedia.model.User;
import cacttus.simplesocialmedia.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final AuthenticationService userService;


    public Comment makeComment(CommentDto commentDto) throws Exception {
        try {
            Post post = postService.getPostById(commentDto.getPostId());

            User user = userService.getUserByID(commentDto.getUserId());


            Comment comment = Comment.builder()
                    .content(commentDto.getContent())
                    .timestamp(LocalDateTime.now())
                    .post(post)
                    .user(user)
                    .build();

           comment = commentRepository.save(comment);
            return comment;
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }


}
