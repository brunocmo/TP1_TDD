import com.unb.CalculoIRPF;
import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;


import static org.junit.Assert.assertEquals;

public class CadastroDeducoesTestFuncionais {

    CalculoIRPF simulador;

    @Before
    public void setup(){
        simulador = new CalculoIRPF();
    }

    @Test
    public void cadastroPrevidenciaOficial1() {
        simulador.cadastrarPrevidenciaOficial("Contribuicao compulsoria", 1000f);
        assertEquals(1000f, simulador.getDeducaoOficialTotal(), 0f);
    }

    @Test
    public void cadastroDependente(){
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.set(2017,3,17);

        simulador.cadastrarDependentes("Pedrinho", dataNascimento );
        assertEquals(189.59f, simulador.getTotalValorDependentes(), 0f);

    }

    @Test
    public void cadastroDePensaoAlimenticia() {
        simulador.cadastrarPensaoAlimenticia(500f);
        assertEquals(500f, simulador.getDeducaoAlimenticiaTotal(), 0f);
    }

    @Test
    public void cadastroOutraDeducoes() {
        simulador.cadastrarOutrasDeducoes( "Funpresp", 800.00f);
        assertEquals(800.00, simulador.getOutrasDeducoesTotal(), 0f);
    }

    @Test
    public void obterValorTotalDeDeducoes() {
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.set(2017,3,17);

        simulador.cadastrarPrevidenciaOficial("Contribuicao compulsoria", 1000f);
        simulador.cadastrarPensaoAlimenticia(500f);
        simulador.cadastrarDependentes("Pedrinho Matador", dataNascimento );
        simulador.cadastrarOutrasDeducoes( "Funpresp", 800.00f);

        assertEquals((1000f+500f+189.59f+800f), simulador.getDeducaoTotal(), 0f);
    }
}
