package br.com.devmanfredi;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class TranferFootballApplication implements TranferFootballInterface {

    List<Time> times = new ArrayList<>();
    List<Jogador> jogadores = new ArrayList<>();


    @Override
    public void novoTime(Long id, String nome, LocalDateTime dataCriacao, BigDecimal dinheiroCaixa) {
        buscarTimePorId(id).isPresent(time -> {
            throw new
        });
    }

    @Override
    public void comprarJogador(Long id, Long idTime, String nacionalidade, String posicao, Integer camisa, Integer habilidade, BigDecimal precoJogador) {

    }

    @Override
    public void venderJogador(Long idJogador) {

    }

    @Override
    public List<Long> buscarTimes() {
        return null;
    }

    @Override
    public Long buscarTimeMaisRico() {
        return null;
    }

    @Override
    public List<String> buscarNomeTime(Long idTime) {
        return null;
    }

    @Override
    public Long buscarTimeMaisAntigo() {
        return null;
    }

    @Override
    public Long buscarTimeMaisNovo() {
        return null;
    }

    @Override
    public BigDecimal buscarSaldo(Long idTime) {
        return null;
    }

    @Override
    public List<Long> buscarJogadores(Long idTime) {
        return null;
    }

    @Override
    public Long buscarJogadorMaisCaro() {
        return null;
    }

    @Override
    public Long buscarJogadorMaisHabilidoso() {
        return null;
    }

    @Override
    public List<Long> buscarMelhoresJogadores() {
        return null;
    }

    @Override
    public List<String> buscarJogadoresPorNacionalidade(String nacionalidade) {
        return null;
    }

    @Override
    public BigDecimal buscarValorDoTime(Long idTime) {
        return null;
    }

    @Override
    public void trocarPosicaoJogador(Long idJogador, String posicao) {

    }

    @Override
    public String buscarPosicao(Long idJogador) {
        return null;
    }

    private Optional<Jogador> buscarJogadorPorId(Long id){
        return jogadores.stream()
                        .filter(jogador -> jogador.getId().equals(id))
                        .findFirst();
    }

    private Optional<Time> buscarTimePorId(Long id){
        return times.stream()
                    .filter(time -> time.getId().equals(id))
                    .findFirst();
    }

    private Optional<Jogador> buscarJogadorPorNacionalidade(String nacionalidade){
        return jogadores.stream()
                        .filter(jogador -> jogador.getNacionalidade().equals(nacionalidade))
                        .findFirst();
    }

}
