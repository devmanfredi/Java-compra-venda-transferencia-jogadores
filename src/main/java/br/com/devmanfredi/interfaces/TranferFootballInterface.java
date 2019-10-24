package br.com.devmanfredi.interfaces;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TranferFootballInterface {

    void novoTime(Long id, String nome, LocalDate dataCriacao, BigDecimal dinheiroCaixa);

    void novoJogador(Long id, Long idTime, String nacionalidade, String posicao, Integer camisa, Integer habilidade, BigDecimal precoJogador);

    void comprarJogador(Long id, Long idTime, String nacionalidade, String posicao, Integer camisa, Integer habilidade, BigDecimal precoJogador);

    void venderJogador(Long idJogador);

    List<Long> buscarTimes();

    Long buscarTimeMaisRico();

    String buscarNomeTime(Long idTime);

    Long buscarTimeMaisAntigo();

    Long buscarTimeMaisNovo();

    BigDecimal buscarSaldo(Long idTime);

    List<Long> buscarJogadores(Long idTime);

    Long buscarJogadorMaisCaro();

    Long buscarJogadorMaisHabilidoso();

    List<Long> buscarMelhoresJogadores();

    List<String> buscarJogadoresPorNacionalidade(String nacionalidade);

    BigDecimal buscarValorDoTime(Long idTime);

    void trocarPosicaoJogador(Long idJogador, String posicao);

    String buscarPosicao(Long idJogador);


}
