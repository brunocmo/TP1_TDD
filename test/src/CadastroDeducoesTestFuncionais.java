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
    public void cadastroPrevidenciaOficial2() {
        simulador.cadastrarPrevidenciaOficial("Outra contribuicao oficial", 200f);
        assertEquals(200f, simulador.getDeducaoOficialTotal(), 0f);
    }


    @Test
    public void cadastroDependentePedrinho(){
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.set(2017,3,17);

        simulador.cadastrarDependentes("Pedrinho", dataNascimento );
        assertEquals(189.59f, simulador.getTotalValorDependentes(), 0f);

    }

    @Test
    public void cadastroDependenteJoaozinho(){
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.set(2020,8,19);

        simulador.cadastrarDependentes("Joaozinho", dataNascimento );
        assertEquals(189.59f, simulador.getTotalValorDependentes(), 0f);

    }

    @Test
    public void cadastroDePensaoAlimenticia1() {
        simulador.cadastrarPensaoAlimenticia(500f);
        assertEquals(500f, simulador.getDeducaoAlimenticiaTotal(), 0f);
    }

    @Test
    public void cadastroDePensaoAlimenticia2() {
        simulador.cadastrarPensaoAlimenticia(1000f);
        assertEquals(1000f, simulador.getDeducaoAlimenticiaTotal(), 0f);
    }

    @Test
    public void cadastroFunprestDeducoes() {
        simulador.cadastrarOutrasDeducoes( "Funpresp", 800.00f);
        assertEquals(800.00, simulador.getOutrasDeducoesTotal(), 0f);
    }

    @Test
    public void cadastroFAPIDeducoes() {
        simulador.cadastrarOutrasDeducoes( "Fapi", 400.00f);
        assertEquals(400.00, simulador.getOutrasDeducoesTotal(), 0f);
    }

    @Test
    public void obterValorTotalDeDeducoes1() {
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.set(2017,3,17);

        simulador.cadastrarPrevidenciaOficial("Contribuicao compulsoria", 1000f);
        simulador.cadastrarPensaoAlimenticia(500f);
        simulador.cadastrarDependentes("Pedrinho Matador", dataNascimento );
        simulador.cadastrarOutrasDeducoes( "Funpresp", 800.00f);

        assertEquals((1000f+500f+189.59f+800f), simulador.getDeducaoTotal(), 0f);
    }

    @Test
    public void obterValorTotalDeDeducoes2() {
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.set(2020,8,19);

        simulador.cadastrarPrevidenciaOficial("Contribuicao compulsoria", 400f);
        simulador.cadastrarPensaoAlimenticia(100f);
        simulador.cadastrarDependentes("Suzane Matadora", dataNascimento );
        simulador.cadastrarOutrasDeducoes( "FAPI", 300.00f);

        assertEquals((400f+100f+189.59f+300.00f), simulador.getDeducaoTotal(), 0f);
    }
}
