import br.com.devmanfredi.TranferFootballApplication;
import br.com.devmanfredi.entity.Time;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TransferFootballApplicationTest {

    private TranferFootballApplication tranferFootballApplication = new TranferFootballApplication();

    @Test
    public void dadoUmTime_QuandoAdicionarNaLista_EntaoDeveRetornarSeuId(){
        Time time = Time.builder()
                .id(10L)
                .nome("Corinthians")
                .dataCriacao(LocalDate.of(1910,9,01))
                .dinheiroCaixa(BigDecimal.valueOf(10000000))
                .build();

        tranferFootballApplication.novoTime(time.getId(),time.getNome(),time.getDataCriacao(), time.getDinheiroCaixa());
        List<Long> result = tranferFootballApplication.buscarTimes();
        Assert.assertThat(result.get(0), Matchers.equalTo(time.getId()));
    }

}
