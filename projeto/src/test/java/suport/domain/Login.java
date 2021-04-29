package suport.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Login {

    @Builder.Default
    private String email = "fulano@qa.com";
    @Builder.Default
    private String password = "teste";

}
