package cacttus.simplesocialmedia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private String title;
    private String description;
    private Integer userId;
}
