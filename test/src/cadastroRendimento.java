import com.unb.calculoIRPF;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class cadastroRendimento {

    private calculoIRPF testeSimulador;

    @Before
    public void setup() {
        testeSimulador = new calculoIRPF();
    }

    @Test
    public void testRealizarUmCadastroRendimento() {
        testeSimulador.cadastrarRendimento("Salário", 10000.00f);
        Assert.assertEquals(10000.00f, testeSimulador.getRendimentoTotal(), 0.0f);

    }

    @Test
    public void DescricaoEmBrancoException() {
        //TODO Não permitir descrição em branco
    }

    @Test
    public  void ValorRendimentoInvalidoException() {
        //TODO Não permitir informar valores de rendimentos em branco ou inválidos (negativos por exemplo)
    }
}
