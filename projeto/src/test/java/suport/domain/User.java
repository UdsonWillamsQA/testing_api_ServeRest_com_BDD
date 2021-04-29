package suport.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @Builder.Default
    private String  nome = "Udson";
    @Builder.Default
    private String email = "udson@gmail.com";
    @Builder.Default
    private String password = "passtest";
    @Builder.Default
    private String administrador = "true";
}
