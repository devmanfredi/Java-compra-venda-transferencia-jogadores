package br.com.devmanfredi.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Time {
    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private BigDecimal dinheiroCaixa;

}
