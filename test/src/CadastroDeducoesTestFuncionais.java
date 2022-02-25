import com.unb.CalculoIRPF;
import com.unb.DescricaoEmBrancoException;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CadastroDeducoesTestFuncionais {

    private CalculoIRPF simulador;

    @Before
    public void setup(){
        simulador = new CalculoIRPF();
    }

    Object[][] contribuicoes;
    float resultadoDeducoesOficial;
    float resultadoDependentes;
    float resultadoPensao;
    float resultadoOutrasDeducoes;
    float resultadoDeducaoTotal;

    public CadastroDeducoesTestFuncionais(
            Object[][] contribuicoes,
            float resultadoDeducoesOficial,
            float resultadoDependentes,
            float resultadoPensao,
            float resultadoOutrasDeducoes,
            float resultadoDeducaoTotal
            ) {
        this.contribuicoes = contribuicoes;
        this.resultadoDeducoesOficial = resultadoDeducoesOficial;
        this.resultadoDependentes = resultadoDependentes;
        this.resultadoPensao = resultadoPensao;
        this.resultadoOutrasDeducoes = resultadoOutrasDeducoes;
        this.resultadoDeducaoTotal = resultadoDeducaoTotal;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parametros = new Object[][]{
                {new Object[][]{
                        {
                            "Contribuicao Compulsoria", 1000f,
                                "Pedrinho", "03/08/2019",
                                500f,
                                "FAPI", 300f
                        },
                }, 1000f, 189.59f, 500f, 300f, (1000f + 189.59f + 500f + 300f) },
                {new Object[][]{
                        {
                                "Outra contribuicao oficial", 200f,
                                "Joaozinho", "13/02/2021",
                                800f,
                                "Funpresp", 500f
                        },
                }, 200f, 189.59f, 800f, 500f, (200f + 189.59f + 800f + 500f)},
                {new Object[][]{
                        {
                                "Contribuicao Compulsoria", 1000f,
                                "Pedrinho", "03/08/2019",
                                500f,
                                "FAPI", 300f
                        },
                        {
                                "Outra contribuicao oficial", 200f,
                                "Joaozinho", "13/02/2021",
                                800f,
                                "Funpresp", 500f
                        },
                }, 1200f, 379.18f, 1300f, 800f, (1200f+379.18f+1300f+800f)},
        };
        return Arrays.asList(parametros);
    }

    @Test
    @Category(TesteFuncional.class)
    public void cadastroPrevidenciasOficiais() {

        for (Object[] contribuicao : contribuicoes){
            simulador.cadastrarPrevidenciaOficial((String)contribuicao[0], (float)contribuicao[1]);
        }
        assertEquals(resultadoDeducoesOficial, simulador.getDeducaoOficialTotal(), 0f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void cadastroDeducaoPorDependentes() {

        for (Object[] contribuicao : contribuicoes){
            simulador.cadastrarDependentes((String)contribuicao[2], (String)contribuicao[3]);
        }
        assertEquals(resultadoDependentes, simulador.getTotalValorDependentes(), 0f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void cadastroPensaoAlimenticia() {

        for (Object[] contribuicao : contribuicoes){
            simulador.cadastrarPensaoAlimenticia((float)contribuicao[4]);
        }
        assertEquals(resultadoPensao, simulador.getDeducaoAlimenticiaTotal(), 0f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void cadastroOutrasDeducoes() {

        for (Object[] contribuicao : contribuicoes){
            simulador.cadastrarOutrasDeducoes((String)contribuicao[5], (float)contribuicao[6]);
        }
        assertEquals(resultadoOutrasDeducoes, simulador.getOutrasDeducoesTotal(), 0f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void resultadoDeducaoTotal() {

        for (Object[] contribuicao : contribuicoes){
            simulador.cadastrarPrevidenciaOficial((String)contribuicao[0], (float)contribuicao[1]);
            simulador.cadastrarDependentes((String)contribuicao[2], (String)contribuicao[3]);
            simulador.cadastrarPensaoAlimenticia((float)contribuicao[4]);
            simulador.cadastrarOutrasDeducoes((String)contribuicao[5], (float)contribuicao[6]);
        }
        assertEquals(resultadoDeducaoTotal, simulador.getDeducaoTotal(), 0f);
    }

    /*
    for (Object[] contribuicao : contribuicoes){
        simulador.cadastrarPrevidenciaOficial((String)contribuicao[0], (float)contribuicao[1]);
        simulador.cadastrarDependentes((String)contribuicao[2], (String)contribuicao[3]);
        simulador.cadastrarPensaoAlimenticia((float)contribuicao[4]);
        simulador.cadastrarOutrasDeducoes((String)contribuicao[5], (float)contribuicao[6]);
    }

    // assertEquals(resultadoDeducoesOficial, simulador.getDeducaoOficialTotal(), 0f);
    // assertEquals(resultadoDependentes, simulador.getTotalValorDependentes(), 0f);
    // assertEquals(resultadoPensao, simulador.getDeducaoAlimenticiaTotal(), 0f);
    // assertEquals(resultadoOutrasDeducoes, simulador.getOutrasDeducoesTotal(), 0f);
    // assertEquals(resultadoDeducaoTotal, simulador.getDeducaoTotal(), 0f);

    */

}
