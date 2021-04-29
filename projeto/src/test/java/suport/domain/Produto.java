package suport.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties
public class Produto {

    @Builder.Default
    private String nome = "Bola";
    @Builder.Default
    private String preco = "15";
    @Builder.Default
    private String descricao = "Uma bola de futebol";
    @Builder.Default
    private String quantidade = "5";
}
