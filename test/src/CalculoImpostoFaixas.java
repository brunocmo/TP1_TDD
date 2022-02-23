import com.unb.CalculoIRPF;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CalculoImpostoFaixas {

    CalculoIRPF simulador;

    @Before
    public void setup() {
        simulador = new CalculoIRPF();
    }

    @Test
    @Category(TesteFuncional.class)
    public void baseDeCalculoValor1() {
        simulador.cadastrarRendimento("Sálario", 10000f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Julio Cesar", "17/07/2017");
        simulador.cadastrarPensaoAlimenticia(2000f);
        simulador.cadastrarOutrasDeducoes("Previdência Privada", 1000f);
        simulador.calcularImposto();

        Assert.assertEquals((10000f-800f-189.59f-2000f-1000f), simulador.calcularBase(), 0.05f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void baseDeCalculoValor2() {
        simulador.cadastrarRendimento("Salário", 7800f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Pedrinho", "20/08/2020");
        simulador.cadastrarDependentes("Joaozinho", "24/07/2021");
        simulador.cadastrarPensaoAlimenticia(0f);
        simulador.cadastrarOutrasDeducoes("FAPI", 500f);
        simulador.calcularImposto();

        Assert.assertEquals( (7800f-800f-379.18f-0f-500f), simulador.calcularBase(), 0.05f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularPrimeiraFaixaValor1() {
        simulador.cadastrarRendimento("Sálario", 10000f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Julio Cesar", "17/07/2017");
        simulador.cadastrarPensaoAlimenticia(2000f);
        simulador.cadastrarOutrasDeducoes("Previdência Privada", 1000f);
        simulador.calcularImposto();

        Assert.assertEquals( 0f , simulador.getPrimeiraFaixa(), 0.05f);

    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularPrimeiraFaixaValo2() {
        simulador.cadastrarRendimento("Salário", 7800f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Pedrinho", "20/08/2020");
        simulador.cadastrarDependentes("Joaozinho", "24/07/2021");
        simulador.cadastrarPensaoAlimenticia(0f);
        simulador.cadastrarOutrasDeducoes("FAPI", 500f);
        simulador.calcularImposto();

        Assert.assertEquals( 0f , simulador.getPrimeiraFaixa(), 0.05f);

    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularSegundaFaixaValor1() {
        simulador.cadastrarRendimento("Sálario", 10000f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Julio Cesar", "17/07/2017");
        simulador.cadastrarPensaoAlimenticia(2000f);
        simulador.cadastrarOutrasDeducoes("Previdência Privada", 1000f);
        simulador.calcularImposto();

        Assert.assertEquals( 922.67f*0.075f , simulador.getSegundaFaixa(), 0.05f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularSegundaFaixaValor2() {
        simulador.cadastrarRendimento("Salário", 7800f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Pedrinho", "20/08/2020");
        simulador.cadastrarDependentes("Joaozinho", "24/07/2021");
        simulador.cadastrarPensaoAlimenticia(0f);
        simulador.cadastrarOutrasDeducoes("FAPI", 500f);
        simulador.calcularImposto();

        Assert.assertEquals( 922.67f*0.075f , simulador.getSegundaFaixa(), 0.05f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularTerceiraFaixaValor1() {
        simulador.cadastrarRendimento("Sálario", 10000f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Julio Cesar", "17/07/2017");
        simulador.cadastrarPensaoAlimenticia(2000f);
        simulador.cadastrarOutrasDeducoes("Previdência Privada", 1000f);
        simulador.calcularImposto();

        Assert.assertEquals( 924.40f*0.15f , simulador.getTerceiraFaixa(), 0.05f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularTerceiraFaixaValor2() {
        simulador.cadastrarRendimento("Salário", 7800f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Pedrinho", "20/08/2020");
        simulador.cadastrarDependentes("Joaozinho", "24/07/2021");
        simulador.cadastrarPensaoAlimenticia(0f);
        simulador.cadastrarOutrasDeducoes("FAPI", 500f);
        simulador.calcularImposto();

        Assert.assertEquals( 924.40f*0.15f , simulador.getTerceiraFaixa(), 0.05f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularQuartaFaixaValor1() {
        simulador.cadastrarRendimento("Sálario", 10000f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Julio Cesar", "17/07/2017");
        simulador.cadastrarPensaoAlimenticia(2000f);
        simulador.cadastrarOutrasDeducoes("Previdência Privada", 1000f);
        simulador.calcularImposto();

        Assert.assertEquals( 913.63f*0.225f , simulador.getQuartaFaixa(), 0.05f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularQuartaFaixaValor2() {
        simulador.cadastrarRendimento("Salário", 7800f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Pedrinho", "20/08/2020");
        simulador.cadastrarDependentes("Joaozinho", "24/07/2021");
        simulador.cadastrarPensaoAlimenticia(0f);
        simulador.cadastrarOutrasDeducoes("FAPI", 500f);
        simulador.calcularImposto();

        Assert.assertEquals( 913.63f*0.225f , simulador.getQuartaFaixa(), 0.05f);
    }


    @Test
    @Category(TesteFuncional.class)
    public void calcularQuintaFaixaValor1() {
        simulador.cadastrarRendimento("Sálario", 10000f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Julio Cesar", "17/07/2017");
        simulador.cadastrarPensaoAlimenticia(2000f);
        simulador.cadastrarOutrasDeducoes("Previdência Privada", 1000f);
        simulador.calcularImposto();

        Assert.assertEquals( 1345.73f*.275f , simulador.getQuintaFaixa(), 0.05f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularQuintaFaixaValor2() {
        simulador.cadastrarRendimento("Salário", 7800f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Pedrinho", "20/08/2020");
        simulador.cadastrarDependentes("Joaozinho", "24/07/2021");
        simulador.cadastrarPensaoAlimenticia(0f);
        simulador.cadastrarOutrasDeducoes("FAPI", 500f);
        simulador.calcularImposto();

        Assert.assertEquals( 1456.14f*.275f , simulador.getQuintaFaixa(), 0.05f);
    }

    @Test
    public void calcularImpostoTotalValor1() {
        simulador.cadastrarRendimento("Sálario", 10000f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Julio Cesar", "17/07/2017");
        simulador.cadastrarPensaoAlimenticia(2000f);
        simulador.cadastrarOutrasDeducoes("Previdência Privada", 1000f);
        simulador.calcularImposto();

        Assert.assertEquals( 783.50f , simulador.getImpostoTotal(), 0.05f);
    }

    @Test
    public void calcularImpostoTotalValor2() {
        simulador.cadastrarRendimento("Salário", 7800f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Pedrinho", "20/08/2020");
        simulador.cadastrarDependentes("Joaozinho", "24/07/2021");
        simulador.cadastrarPensaoAlimenticia(0f);
        simulador.cadastrarOutrasDeducoes("FAPI", 500f);
        simulador.calcularImposto();

        Assert.assertEquals( 813.86f , simulador.getImpostoTotal(), 0.05f);
    }
}
