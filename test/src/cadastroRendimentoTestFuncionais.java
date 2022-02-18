import com.unb.calculoIRPF;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class cadastroRendimentoTestFuncionais {

    private calculoIRPF testeSimulador;

    //Atributos do objeto de teste
    Object[][] rendimentos;
    float valorEsperado;

    @Before
    public void setup() {
        testeSimulador = new calculoIRPF();
    }


    public cadastroRendimentoTestFuncionais(Object[][] rendimentos, float valorEsperado) {
        this.rendimentos = rendimentos;
        this.valorEsperado = valorEsperado;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parametros = new Object[][]{
                {new Object[][]{
                        {"Salário", 10000f},
                }, 10000.00f},
                {new Object[][]{
                        {"Salário", 8000.00f},
                }, 8000.00f},
                {new Object[][]{
                        {"Salario", 10000.00f},
                        {"Aluguel", 2000.00f}
                }, 12000.00f}
        };
        return Arrays.asList(parametros);
    }

    @Test
    @Category(TesteFuncional.class)
    public void testCadastroRendimentos() {
        for (Object[] testes : rendimentos) {
            testeSimulador.cadastrarRendimento((String)testes[0], (float)testes[1]);
        }
        Assert.assertEquals(valorEsperado, testeSimulador.getRendimentoTotal(), 0f);
    }

}
