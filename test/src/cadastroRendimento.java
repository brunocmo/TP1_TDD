import com.unb.DescricaoEmBrancoException;
import com.unb.ValorRendimentoInvalidoException;
import com.unb.calculoIRPF;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;
import org.junit.experimental.categories.Category;


public class cadastroRendimento {

    private calculoIRPF testeSimulador;

    @Before
    public void setup() {
        testeSimulador = new calculoIRPF();
    }

    @Test
    @Category(TesteFuncional.class)
    public void testRealizarUmCadastroRendimento() {
        testeSimulador.cadastrarRendimento("Salário", 10000.00f);
        Assert.assertEquals(10000.00f, testeSimulador.getRendimentoTotal(), 0.0f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void testRealizarUmOutroCadastrosRendimento() {
        testeSimulador.cadastrarRendimento("Salário", 8000.00f);
        Assert.assertEquals(8000.00f, testeSimulador.getRendimentoTotal(), 0.0f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void testRealizarDoisCadastrosRendimento() {
        testeSimulador.cadastrarRendimento("Salário", 10000.00f);
        testeSimulador.cadastrarRendimento("Aluguel", 2000.00f);
        Assert.assertEquals(12000.00f, testeSimulador.getRendimentoTotal(), 0.0f);
    }

    @Test(expected = DescricaoEmBrancoException.class)
    @Category(TesteExececao.class)
    public void testRendimentoEmBranco() {
        testeSimulador.cadastrarRendimento("", 1000.00f);
    }

    @Test(expected = ValorRendimentoInvalidoException.class)
    @Category(TesteExececao.class)
    public void testValorInvalido() {
        testeSimulador.cadastrarRendimento("Salário", 0.0f);
    }
}
