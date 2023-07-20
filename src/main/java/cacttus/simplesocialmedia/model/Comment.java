package cacttus.simplesocialmedia.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comments")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String content;
    private LocalDateTime timestamp;

 @JoinColumn(name = "To post with id")
    @OneToOne
    private Post post;

   @JoinColumn(name = "comment by user_id")
    @OneToOne
    private User user;

}
