package cacttus.simplesocialmedia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthenticationResponseDto {
    private Integer id;
    private String token;
    private String email;
}
