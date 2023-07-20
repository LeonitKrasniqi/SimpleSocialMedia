package cacttus.simplesocialmedia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String content;
    private Integer postId;
    private Integer userId;

}
