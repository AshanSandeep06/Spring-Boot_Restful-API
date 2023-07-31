package lk.epic.restfulAPI.util;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class AuthenticationResponse {
    private String token;
}
