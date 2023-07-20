package cacttus.simplesocialmedia.repository;

import cacttus.simplesocialmedia.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Override
    Optional<Post> findById(Integer id);
    void deleteAllByTimestampBefore(LocalDateTime timestamp);


}
