package cacttus.simplesocialmedia.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "posts")

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private LocalDateTime timestamp;
    @OneToOne
    @JoinColumn(name = "posted_by")
    private User user;


}
