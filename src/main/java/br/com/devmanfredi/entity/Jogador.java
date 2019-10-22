package br.com.devmanfredi.entity;

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
    private String nome;
    private Long idTime;
    private String nacionalidade;
    private String posicao;
    private Integer camisa;
    private Integer habilidade;
    private BigDecimal precoJogador;

    public Optional<Long> getIdTime(){
        return Optional.ofNullable(idTime);
    }

}

