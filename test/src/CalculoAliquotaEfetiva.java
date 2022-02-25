import com.unb.CalculoIRPF;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CalculoAliquotaEfetiva {

    private CalculoIRPF simulador;

    Object[][] registros;
    float valorEsperado;

    @Before
    public void setup(){
        simulador = new CalculoIRPF();
    }

    public CalculoAliquotaEfetiva(Object[][] registros, float valorEsperado) {
        this.registros = registros;
        this.valorEsperado = valorEsperado;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parametros = new Object[][]{
                {new Object[][]{
                        {
                                "Salario", 3500f,
                                "Contribuicao Compulsoria", 1000f,
                                "Pedrinho", "03/08/2019",
                                500f,
                                "FAPI", 300f
                        },
                }, 0.0f },
                {new Object[][]{
                        {
                                "Salario", 6300f,
                                "Outra contribuicao oficial", 400f,
                                "Joaozinho", "13/02/2021",
                                800f,
                                "Funpresp", 500f
                        },
                }, 0.0565f },
                {new Object[][]{
                        {
                                "Salario", 4300f,
                                "Previdencia Oficial", 400f,
                                "", "",
                                0f,
                                "Previdencia Privada", 600f
                        },
                }, 0.0326f },
                {new Object[][]{
                        {
                                "Salario", 12000f,
                                "Contribuicao Compulsoria", 1000f,
                                "Pedrinho", "03/08/2019",
                                500f,
                                "FAPI", 300f
                        },
                        {
                                "Aluguel", 3000f,
                                "Outra contribuicao oficial", 500f,
                                "Joaozinho", "13/02/2021",
                                800f,
                                "Funpresp", 500f
                        },
                }, 0.1440f },
        };
        return Arrays.asList(parametros);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcAliquotaEfetiva() {
        for (Object[] registro : registros){
            simulador.cadastrarRendimento(((String)registro[0]), (float)registro[1]);
            simulador.cadastrarPrevidenciaOficial(((String)registro[2]), (float)registro[3]);
            simulador.cadastrarDependentes((String)registro[4], (String)registro[5]);
            simulador.cadastrarPensaoAlimenticia((float)registro[6]);
            simulador.cadastrarOutrasDeducoes((String)registro[7], (float)registro[8]);
            simulador.calcularImposto();
        }
        Assert.assertEquals( valorEsperado, simulador.getCalculoAliquotaEfetiva(), 0.05f);
    }
}
