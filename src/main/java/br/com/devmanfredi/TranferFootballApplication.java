package br.com.devmanfredi;

import br.com.devmanfredi.entity.Jogador;
import br.com.devmanfredi.entity.Time;
import br.com.devmanfredi.exceptions.IdentificadorUtilizadoException;
import br.com.devmanfredi.exceptions.JogadorNaoEncontradoException;
import br.com.devmanfredi.exceptions.SaldoInsuficienteException;
import br.com.devmanfredi.exceptions.TimeNaoEncontradoException;
import br.com.devmanfredi.interfaces.TranferFootballInterface;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TranferFootballApplication implements TranferFootballInterface {

    List<Time> times = new ArrayList<>();
    List<Jogador> jogadores = new ArrayList<>();


    @Override
    public void novoTime(Long id, String nome, LocalDate dataCriacao, BigDecimal dinheiroCaixa) {
        buscarTimePorId(id).ifPresent(time -> {
            throw new IdentificadorUtilizadoException();
        });
        times.add(Time.builder()
             .id(id)
             .nome(nome)
             .dataCriacao(dataCriacao)
             .dinheiroCaixa(dinheiroCaixa)
             .build());
    }

    @Override
    public void comprarJogador(Long id, Long idTime, String nacionalidade, String posicao, Integer camisa, Integer habilidade, BigDecimal precoJogador) {
        buscarTimePorId(idTime).ifPresent(time -> {
            throw new IdentificadorUtilizadoException();
        });
        buscarJogadorPorId(id).ifPresent(jogador -> {
            throw new IdentificadorUtilizadoException();
        });

        Time time = buscarTimePorId(idTime).orElseThrow(TimeNaoEncontradoException::new);
        Jogador jogador = buscarJogadorPorId(id).orElseThrow(JogadorNaoEncontradoException::new);

        if(time.getDinheiroCaixa().compareTo(jogador.getPrecoJogador()) >= 0){
            jogador.setIdTime(time.getId());
        }else {
            throw new SaldoInsuficienteException();
        }
    }

    @Override
    public void venderJogador(Long idJogador) {
        Jogador jogador = buscarJogadorPorId(idJogador).orElseThrow(JogadorNaoEncontradoException::new);
        Time time = buscarTimePorId(jogador.getIdTime().get()).orElseThrow(TimeNaoEncontradoException::new);
        time.setDinheiroCaixa(jogador.getPrecoJogador().add(jogador.getPrecoJogador()));
        jogador.setIdTime(null);
    }

    @Override
    public List<Long> buscarTimes() {
        return times.stream()
                    .sorted(Comparator.comparing(Time::getId))
                    .map(Time::getId)
                    .collect(Collectors.toList());
    }

    @Override
    public Long buscarTimeMaisRico() {
        return times.stream()
                    .sorted(Comparator.comparing(Time::getId))
                    .max(Comparator.comparing(Time::getDinheiroCaixa))
                    .map(Time::getId)
                    .orElseThrow(TimeNaoEncontradoException::new);

    }

    @Override
    public String buscarNomeTime(Long idTime) {
        buscarTimePorId(idTime).orElseThrow(TimeNaoEncontradoException::new);
        return times.stream()
                    .filter(time -> time.getId().equals(idTime))
                    .map(Time::getNome)
                    .findFirst()
                    .orElseThrow(TimeNaoEncontradoException::new);
    }

    @Override
    public Long buscarTimeMaisAntigo() {
        return times.stream()
                    .sorted(Comparator.comparing(Time::getId))
                    .max(Comparator.comparing(Time::getDataCriacao))
                    .map(Time::getId)
                    .orElseThrow(TimeNaoEncontradoException::new);
    }

    @Override
    public Long buscarTimeMaisNovo() {
        return times.stream()
                    .sorted(Comparator.comparing(Time::getId))
                    .min(Comparator.comparing(Time::getDataCriacao))
                    .map(Time::getId)
                    .orElseThrow(TimeNaoEncontradoException::new);
    }

    @Override
    public BigDecimal buscarSaldo(Long idTime) {
        buscarTimePorId(idTime).orElseThrow(TimeNaoEncontradoException::new);
        return times.stream()
                    .filter(time -> time.getId().equals(idTime))
                    .map(Time::getDinheiroCaixa)
                    .findFirst()
                    .orElseThrow(TimeNaoEncontradoException::new);
    }

    @Override
    public List<Long> buscarJogadores(Long idTime) {
        buscarTimePorId(idTime).orElseThrow(TimeNaoEncontradoException::new);
        return jogadores.stream()
                        .filter(jogador -> jogador.getId().equals(idTime))
                        .map(Jogador::getId)
                        .collect(Collectors.toList());
    }

    @Override
    public Long buscarJogadorMaisCaro() {
        return jogadores.stream()
                        .sorted(Comparator.comparing(Jogador::getId))
                        .max(Comparator.comparing(Jogador::getPrecoJogador))
                        .map(Jogador::getId)
                        .orElseThrow(JogadorNaoEncontradoException::new);
    }

    @Override
    public Long buscarJogadorMaisHabilidoso() {
        return jogadores.stream()
                        .sorted(Comparator.comparing(Jogador::getId))
                        .max(Comparator.comparing(Jogador::getHabilidade))
                        .map(Jogador::getId)
                        .orElseThrow(JogadorNaoEncontradoException::new);
    }

    @Override
    public List<Long> buscarMelhoresJogadores() {
        return jogadores.stream()
                        .filter(jogador -> jogador.getHabilidade() >= 80)
                        .map(Jogador::getId)
                        .collect(Collectors.toList());
    }

    @Override
    public List<String> buscarJogadoresPorNacionalidade(String nacionalidade) {
        return jogadores.stream()
                        .filter(jogador -> jogador.getNacionalidade().equals(nacionalidade))
                        .map(Jogador::getNome)
                        .collect(Collectors.toList());
    }

    @Override
    public BigDecimal buscarValorDoTime(Long idTime) {
        buscarTimePorId(idTime).orElseThrow(TimeNaoEncontradoException::new);
        return times.stream()
                    .filter(time -> time.getId().equals(idTime))
                    .map(Time::getDinheiroCaixa)
                    .findFirst()
                    .orElseThrow(TimeNaoEncontradoException::new);
    }

    @Override
    public void trocarPosicaoJogador(Long idJogador, String posicao) {
        Jogador jogador = buscarJogadorPorId(idJogador).orElseThrow(JogadorNaoEncontradoException::new);
        jogador.setPosicao(posicao);
    }

    @Override
    public String buscarPosicao(Long idJogador) {
        return jogadores.stream()
                        .filter(jogador -> jogador.getId().equals(idJogador))
                        .map(Jogador::getPosicao)
                        .findFirst()
                        .orElseThrow(JogadorNaoEncontradoException::new);
    }

    private Optional<Jogador> buscarJogadorPorNacionalidade(String nacionalidade){
        return jogadores.stream()
                .filter(jogador -> jogador.getNacionalidade().equals(nacionalidade))
                .findFirst();
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

}
