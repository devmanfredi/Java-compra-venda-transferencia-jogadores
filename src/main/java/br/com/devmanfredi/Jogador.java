package br.com.devmanfredi;

import lombok.*;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Jogador {
    private Long id;
    private Long idTime;
    private String nacionalidade;
    private String posicao;
    private Integer camisa;
    private Integer habilidade;
    private BigDecimal preco;

    public Optional<Long> getIdTime(){
        return Optional.ofNullable(idTime);
    }

}

