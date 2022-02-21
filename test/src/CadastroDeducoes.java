import com.unb.CalculoIRPF;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CadastroDeducoes {
    private CalculoIRPF simulador;

    @Before
    public void setup(){
        simulador = new CalculoIRPF();
    }

    //atributo do objeto de teste
    Object[][] contribuicoes;
    float valorEsperado;
    int qntDependentes;

    // construtor do objeto de teste
    public CadastroDeducoes(Object[][] contribuicoes, int qntDepen, float valorEsperado) {
        this.contribuicoes = contribuicoes;
        this.valorEsperado = valorEsperado;
        this.qntDependentes = qntDepen;
    }

    // caso de teste
    @Test
    public void cadastroContribuicoes(){
        simulador.cadastrarDependentes(this.qntDependentes);
        for (Object[] contribuicao : contribuicoes){
            simulador.cadastrarPrevidenciaOficial((String)contribuicao[0], (float)contribuicao[1]);
        }
        assertEquals(valorEsperado, simulador.getTotalDeducaoPrevidenciaOficial(), 0.1f);
    }


    @Parameters
    public static Collection<Object[]> getParameters(){
        Object[][] resposta = new Object[][]{
                {new Object[][]{
                        {"Contribuicao Compulsoria", 1000f}
                }, 1, 1189.59f},

                {new Object[][]{
                        {"Contribuicao Compulsoria", 800f}
                }, 2, 1179.18f},

                {new Object[][]{
                        {"Contribuicao Compulsoria", 1800f}
                }, 4, 2558.36f},

                {new Object[][]{
                        {"Contribuicao Compulsoria", 1800f},
                        {"Carne INSS", 800f}
                }, 3, 3168.77f},

                {new Object[][]{
                        {"Contribuicao Compulsoria", 1800f},
                        {"Carne INSS1", 200f},
                        {"Carne INSS2", 2000f}
                }, 3, 4568.77f},

                {new Object[][]{
                        {"Contribuicao Compulsoria", 1800f},
                        {"Carne INSS1", 200f},
                        {"Carne INSS2", 2000f},
                        {"Carne INSS3", 500f}
                }, 5, 5447.95f},
        };
        return Arrays.asList(resposta);
    }

    /*@Test
    public void cadastroPrevidenciaOficial1() {
        simulador.cadastrarPrevidenciaOficial("Contribuicao compulsoria", 1000f);
        assertEquals(1000f, simulador.getTotalDeducaoPrevidenciaOficial(), 0f);
    }

    @Test
    public void cadastroPrevidenciaOficial2() {
        simulador.cadastrarPrevidenciaOficial("Contribuicao compulsoria", 800f);
        assertEquals(800f, simulador.getTotalDeducaoPrevidenciaOficial(), 0f);
    }

    @Test
    public void cadastroPrevidenciaOficial3() {
        simulador.cadastrarPrevidenciaOficial("Contribuicao compulsoria", 1000f);
        simulador.cadastrarPrevidenciaOficial("Carne INSS", 800f);
        assertEquals(1800f, simulador.getTotalDeducaoPrevidenciaOficial(), 0f);
    } */

    /*
    @Test
    public void cadastroDependente1(){
        simulador.cadastrarDependentes(1);
        assertEquals(189.59f, simulador.getTotalValorDependentes(), 0f);
    }

    @Test
    public void cadastroDependente2(){
        simulador.cadastrarDependentes(2);
        assertEquals(189.59f*2, simulador.getTotalValorDependentes(), 0f);
    }

    @Test
    public void cadastroDependente3(){
        simulador.cadastrarDependentes(5);
        assertEquals(189.59f*5, simulador.getTotalValorDependentes(), 0f);
    }
    */
}
