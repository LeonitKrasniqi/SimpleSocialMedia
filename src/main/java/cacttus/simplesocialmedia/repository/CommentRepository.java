package cacttus.simplesocialmedia.repository;

import cacttus.simplesocialmedia.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    void deleteAllByTimestampBefore(LocalDateTime timestamp);


}
