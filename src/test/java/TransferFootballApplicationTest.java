import br.com.devmanfredi.TranferFootballApplication;
import br.com.devmanfredi.entity.Jogador;
import br.com.devmanfredi.entity.Time;
import br.com.devmanfredi.exceptions.JogadorNaoEncontradoException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TransferFootballApplicationTest {

    private TranferFootballApplication tranferFootballApplication = new TranferFootballApplication();

    @Test
    public void dadoUmTime_QuandoAdicionarNaLista_EntaoDeveRetornarSeuId(){
        Time time = buildTime(10L);
        tranferFootballApplication.novoTime(time.getId(),time.getNome(),time.getDataCriacao(), time.getDinheiroCaixa());
        List<Long> result = tranferFootballApplication.buscarTimes();
        Assert.assertThat(result.get(0), Matchers.equalTo(time.getId()));
    }

    @Test
    public void dadoUmTimeEUmJogador_QuandoAdicionarAmbosNasListasEOTimeTiverSaldo_EntaoCompraJogador(){
        //salvar um time
        Time time = buildTime(10L);
        Jogador jogador = buildJogador(20L);
        tranferFootballApplication.novoTime(time.getId(),time.getNome(),time.getDataCriacao(), time.getDinheiroCaixa());
        tranferFootballApplication.novoJogador(jogador.getId(),
                                                time.getId(),
                                                jogador.getNacionalidade(),
                                                jogador.getPosicao(),
                                                jogador.getCamisa(),
                                                jogador.getHabilidade(),
                                                jogador.getPrecoJogador());

        tranferFootballApplication.comprarJogador(jogador.getId(),
                                                time.getId(),
                                                jogador.getNacionalidade(),
                                                jogador.getPosicao(),
                                                jogador.getCamisa(),
                                                jogador.getHabilidade(),
                                                jogador.getPrecoJogador());

        Jogador jogadorComIdDoTime = tranferFootballApplication.buscarJogadorPorId(jogador.getId()).orElseThrow(JogadorNaoEncontradoException::new);
        Assert.assertThat(jogadorComIdDoTime.getIdTime(), Matchers.equalTo(Optional.ofNullable(time.getId())));
    }

    private Jogador buildJogador(Long id) {
        return Jogador.builder()
                .id(id)
                .idTime(null)
                .nome("Jogador")
                .posicao("Atacante")
                .camisa(10)
                .habilidade(88)
                .nacionalidade("BR")
                .precoJogador(BigDecimal.valueOf(100000))
                .build();
    }

    private Time buildTime(Long id) {
        return Time.builder()
                .id(id)
                .nome("Corinthians")
                .dataCriacao(LocalDate.of(1910,9,01))
                .dinheiroCaixa(BigDecimal.valueOf(10000000))
                .build();
    }

}
