package lk.epic.restfulAPI.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class LoginDTO {
    private String email;
    private String password;
}
