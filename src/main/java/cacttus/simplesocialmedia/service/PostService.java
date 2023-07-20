package cacttus.simplesocialmedia.service;

import cacttus.simplesocialmedia.dto.PostDto;
import cacttus.simplesocialmedia.model.Post;
import cacttus.simplesocialmedia.model.User;
import cacttus.simplesocialmedia.repository.CommentRepository;
import cacttus.simplesocialmedia.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final AuthenticationService userService;
    private final CommentRepository commentRepository;

    public Post createPost(PostDto postDto) throws Exception {
        try {
            User user = userService.getUserByID(postDto.getUserId());
            if (user == null) {
                throw new IllegalArgumentException("User not found");
            }


            Optional<Post> existingPost = postRepository.findById(postDto.getUserId());
            if (existingPost.isPresent()) {
                throw new IllegalArgumentException("User has already created a post");
            }

            Post post = Post.builder().
                    title(postDto.getTitle()).description(postDto.getDescription()).timestamp(LocalDateTime.now()).user(user).build();
            post = postRepository.save(post);
            return post;

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }



    @Transactional
    @Scheduled(fixedRate = 120000)
    public void deleteCommentsAndPostsAfter24Hours() {
        log.info("Running the job for deleting comments and posts after 1 day");
        try {
            LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);

            commentRepository.deleteAllByTimestampBefore(twentyFourHoursAgo);

            postRepository.deleteAllByTimestampBefore(twentyFourHoursAgo);

            log.info("Deleted comments and posts before: " + twentyFourHoursAgo);
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }


    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }



    public Post getPostById(Integer id) {
        var post = postRepository.findById(id);
        return post.orElse(null);
    }

}
