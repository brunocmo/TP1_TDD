import com.unb.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CadastroDeducoesTestExcecoes {
    private CalculoIRPF testeSimulador;

    @Before
    public void setup() {
        testeSimulador = new CalculoIRPF();
    }

    @Test(expected = DescricaoEmBrancoException.class)
    @Category(TesteExececao.class)
    public void testDescricaoPrevidenciaOficialEmBranco() {
        testeSimulador.cadastrarPrevidenciaOficial("", 1000.00f);
    }

    @Test(expected = ValorDeducaoInvalidoException.class)
    @Category(TesteExececao.class)
    public void testValorPrevidenciaOficialInvalido() {
        testeSimulador.cadastrarPrevidenciaOficial("Salário", 0.0f);
    }

    @Test(expected = ValorDeducaoInvalidoException.class)
    @Category(TesteExececao.class)
    public void testValorPensaoAlimenticiaInvalido() {
        testeSimulador.cadastrarPensaoAlimenticia(-1.0f);
    }

    @Test(expected = NomeEmBrancoException.class)
    @Category(TesteExececao.class)
    public void testNomeDependenteEmBranco() {
        testeSimulador.cadastrarDependentes("", "25/09/2018");
    }

    @Test(expected = DescricaoEmBrancoException.class)
    @Category(TesteExececao.class)
    public void testDescricaoOutrasDeducoesEmBranco() {
        testeSimulador.cadastrarOutrasDeducoes("", 1090.00f);
    }

    @Test(expected = ValorDeducaoInvalidoException.class)
    @Category(TesteExececao.class)
    public void testValorOutrasDeducoesInvalido() {
        testeSimulador.cadastrarOutrasDeducoes("Dentista", -1.0f);
    }
}