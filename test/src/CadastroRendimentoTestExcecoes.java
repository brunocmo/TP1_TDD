import com.unb.DescricaoEmBrancoException;
import com.unb.ValorRendimentoInvalidoException;
import com.unb.CalculoIRPF;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CadastroRendimentoTestExcecoes {

    private CalculoIRPF testeSimulador;

    @Before
    public void setup() {
        testeSimulador = new CalculoIRPF();
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
