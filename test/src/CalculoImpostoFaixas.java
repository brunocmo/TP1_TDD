import com.unb.CalculoIRPF;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalculoImpostoFaixas {

    private CalculoIRPF simulador;

    @Before
    public void setup(){
        simulador = new CalculoIRPF();
    }


    Object[][] impostos;
    float baseCalculo;
    float PrimeiraFaixa;
    float SegundaFaixa;
    float TerceiraFaixa;
    float QuartaFaixa;
    float QuintaFaixa;
    float impostoTotal;

    public CalculoImpostoFaixas(
            Object[][] impostos,
            float baseCalculo,
            float PrimeiraFaixa,
            float SegundaFaixa,
            float TerceiraFaixa,
            float QuartaFaixa,
            float QuintaFaixa,
            float impostoTotal
    ) {
        this.impostos = impostos;
        this.baseCalculo = baseCalculo;
        this.PrimeiraFaixa = PrimeiraFaixa;
        this.SegundaFaixa = SegundaFaixa;
        this.TerceiraFaixa = TerceiraFaixa;
        this.QuartaFaixa = QuartaFaixa;
        this.QuintaFaixa = QuintaFaixa;
        this.impostoTotal = impostoTotal;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parametros = new Object[][]{
                {new Object[][]{
                        {
                                "Salário", 10000f,
                                "Contribuicao Compulsoria", 1000f,
                                "Pedrinho", "03/08/2019",
                                500f,
                                "FAPI", 300f
                        },
                }, 8010.41f, 0f, (922.67f*0.075f), (924.40f*0.15f), (913.63f*0.225f), (3345.73f*0.275f), 1333.50f },
                {new Object[][]{
                        {
                                "Salário", 7800f,
                                "Outra contribuicao oficial", 200f,
                                "Joaozinho", "13/02/2021",
                                800f,
                                "Funpresp", 500f
                        },
                }, 6110.41f, 0f, (922.67f*0.075f), (924.40f*0.15f), (913.63f*0.225f), (1445.73f*0.275f), 811.00f },
                {new Object[][]{
                        {
                                "Alugel", 3200f,
                                "Previdencia oficial", 400f,
                                "Ricardo", "13/02/2021",
                                0f,
                                "FAPI", 200f
                        },
                }, 2410.41f, 0f, (506.43f*0.075f), (0f*0.15f), (0f*0.225f), (0f*0.275f), 37.98f },
                {new Object[][]{
                        {
                                "Salario", 4500f,
                                "Previdencia oficial", 400f,
                                "", "",
                                0f,
                                "Previdencia privada", 200f
                        },
                }, 3900f, 0f, (922.67f*0.075f), (924.40f*0.15f), (148.95f*0.225f), (0f*0.275f), 241.37f },
                {new Object[][]{
                        {
                                "Trade", 4127.34f,
                                "Previdencia oficial", 400.84f,
                                "", "",
                                0f,
                                "Previdencia privada", 374.13f
                        },
                }, 3352.37f, 0f, (922.67f*0.075f), (525.72f*0.15f), (0.0f*0.225f), (0.0f*0.275f), 148.05f },
                {new Object[][]{
                        {
                                "Salário", 10000f,
                                "Contribuicao Compulsoria", 1000f,
                                "Pedrinho", "03/08/2019",
                                500f,
                                "FAPI", 300f
                        },
                        {
                                "Aluguel", 7800f,
                                "Outra contribuicao oficial", 200f,
                                "Joaozinho", "13/02/2021",
                                800f,
                                "Funpresp", 500f
                        },
                }, 14120.82f, 0f, (922.67f*0.075f), (924.40f*0.15f), (913.63f*0.225f), (9456.14f*0.275f), 3013.86f }
        };
        return Arrays.asList(parametros);
    }

    @Test
    @Category(TesteFuncional.class)
    public void baseDeCalculoValor() {
        for (Object[] imposto : impostos){
            simulador.cadastrarRendimento(((String)imposto[0]), (float)imposto[1]);
            simulador.cadastrarPrevidenciaOficial(((String)imposto[2]), (float)imposto[3]);
            simulador.cadastrarDependentes((String)imposto[4], (String)imposto[5]);
            simulador.cadastrarPensaoAlimenticia((float)imposto[6]);
            simulador.cadastrarOutrasDeducoes((String)imposto[7], (float)imposto[8]);
        }
        Assert.assertEquals( baseCalculo, simulador.calcularBase(), 0.05f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularPrimeiraFaixa() {
        for (Object[] imposto : impostos){
            simulador.cadastrarRendimento(((String)imposto[0]), (float)imposto[1]);
            simulador.cadastrarPrevidenciaOficial(((String)imposto[2]), (float)imposto[3]);
            simulador.cadastrarDependentes((String)imposto[4], (String)imposto[5]);
            simulador.cadastrarPensaoAlimenticia((float)imposto[6]);
            simulador.cadastrarOutrasDeducoes((String)imposto[7], (float)imposto[8]);
            simulador.calcularImposto();
        }
        Assert.assertEquals( PrimeiraFaixa, simulador.getPrimeiraFaixa(), 0.05f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularSegundaFaixa() {
        for (Object[] imposto : impostos){
            simulador.cadastrarRendimento(((String)imposto[0]), (float)imposto[1]);
            simulador.cadastrarPrevidenciaOficial(((String)imposto[2]), (float)imposto[3]);
            simulador.cadastrarDependentes((String)imposto[4], (String)imposto[5]);
            simulador.cadastrarPensaoAlimenticia((float)imposto[6]);
            simulador.cadastrarOutrasDeducoes((String)imposto[7], (float)imposto[8]);
            simulador.calcularImposto();
        }
        Assert.assertEquals( SegundaFaixa, simulador.getSegundaFaixa(), 0.05f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularTerceiraFaixa() {
        for (Object[] imposto : impostos){
            simulador.cadastrarRendimento(((String)imposto[0]), (float)imposto[1]);
            simulador.cadastrarPrevidenciaOficial(((String)imposto[2]), (float)imposto[3]);
            simulador.cadastrarDependentes((String)imposto[4], (String)imposto[5]);
            simulador.cadastrarPensaoAlimenticia((float)imposto[6]);
            simulador.cadastrarOutrasDeducoes((String)imposto[7], (float)imposto[8]);
            simulador.calcularImposto();
        }
        Assert.assertEquals( TerceiraFaixa, simulador.getTerceiraFaixa(), 0.05f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularQuartaFaixa() {
        for (Object[] imposto : impostos){
            simulador.cadastrarRendimento(((String)imposto[0]), (float)imposto[1]);
            simulador.cadastrarPrevidenciaOficial(((String)imposto[2]), (float)imposto[3]);
            simulador.cadastrarDependentes((String)imposto[4], (String)imposto[5]);
            simulador.cadastrarPensaoAlimenticia((float)imposto[6]);
            simulador.cadastrarOutrasDeducoes((String)imposto[7], (float)imposto[8]);
            simulador.calcularImposto();
        }
        Assert.assertEquals( QuartaFaixa, simulador.getQuartaFaixa(), 0.05f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularQuintaFaixa() {
        for (Object[] imposto : impostos){
            simulador.cadastrarRendimento(((String)imposto[0]), (float)imposto[1]);
            simulador.cadastrarPrevidenciaOficial(((String)imposto[2]), (float)imposto[3]);
            simulador.cadastrarDependentes((String)imposto[4], (String)imposto[5]);
            simulador.cadastrarPensaoAlimenticia((float)imposto[6]);
            simulador.cadastrarOutrasDeducoes((String)imposto[7], (float)imposto[8]);
            simulador.calcularImposto();
        }
        Assert.assertEquals( QuintaFaixa, simulador.getQuintaFaixa(), 0.05f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularImpostoTotal() {
        for (Object[] imposto : impostos){
            simulador.cadastrarRendimento(((String)imposto[0]), (float)imposto[1]);
            simulador.cadastrarPrevidenciaOficial(((String)imposto[2]), (float)imposto[3]);
            simulador.cadastrarDependentes((String)imposto[4], (String)imposto[5]);
            simulador.cadastrarPensaoAlimenticia((float)imposto[6]);
            simulador.cadastrarOutrasDeducoes((String)imposto[7], (float)imposto[8]);
            simulador.calcularImposto();
        }
        Assert.assertEquals( impostoTotal, simulador.getImpostoTotal(), 0.05f);
    }

}
