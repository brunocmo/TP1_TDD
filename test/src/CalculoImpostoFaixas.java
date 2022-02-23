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
    public void baseDeCalculo() {
        simulador.cadastrarRendimento("Sálario", 10000f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Julio Cesar", "17/07/2017");
        simulador.cadastrarPensaoAlimenticia(2000f);
        simulador.cadastrarOutrasDeducoes("Previdência Privada", 1000f);

        Assert.assertEquals((10000f-800f-189.59f-2000f-1000f), simulador.calcularBase(), 0f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularPrimeiraFaixa() {
        simulador.cadastrarRendimento("Sálario", 10000f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Julio Cesar", "17/07/2017");
        simulador.cadastrarPensaoAlimenticia(2000f);
        simulador.cadastrarOutrasDeducoes("Previdência Privada", 1000f);

        Assert.assertEquals( 0f , simulador.getPrimeiraFaixa(), 0f);

    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularSegundaFaixa() {
        simulador.cadastrarRendimento("Sálario", 10000f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Julio Cesar", "17/07/2017");
        simulador.cadastrarPensaoAlimenticia(2000f);
        simulador.cadastrarOutrasDeducoes("Previdência Privada", 1000f);

        Assert.assertEquals( 922.67f*0.075f , simulador.getSegundaFaixa(), 0f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularTerceiraFaixa() {
        simulador.cadastrarRendimento("Sálario", 10000f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Julio Cesar", "17/07/2017");
        simulador.cadastrarPensaoAlimenticia(2000f);
        simulador.cadastrarOutrasDeducoes("Previdência Privada", 1000f);

        Assert.assertEquals( 924.40f*0.15f , simulador.getTerceiraFaixa(), 0f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularQuartaFaixa() {
        simulador.cadastrarRendimento("Sálario", 10000f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Julio Cesar", "17/07/2017");
        simulador.cadastrarPensaoAlimenticia(2000f);
        simulador.cadastrarOutrasDeducoes("Previdência Privada", 1000f);

        Assert.assertEquals( 913.63f*0.225f , simulador.getQuartaFaixa(), 0f);
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcularQuintaFaixa() {
        simulador.cadastrarRendimento("Sálario", 10000f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Julio Cesar", "17/07/2017");
        simulador.cadastrarPensaoAlimenticia(2000f);
        simulador.cadastrarOutrasDeducoes("Previdência Privada", 1000f);

        Assert.assertEquals( 1345.73f*.275f , simulador.getQuintaFaixa(), 0f);
    }

    @Test
    public void calcularImpostoTotal() {
        simulador.cadastrarRendimento("Sálario", 10000f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Julio Cesar", "17/07/2017");
        simulador.cadastrarPensaoAlimenticia(2000f);
        simulador.cadastrarOutrasDeducoes("Previdência Privada", 1000f);

        Assert.assertEquals( 783.50f , simulador.getImpostoTotal(), 0f);
    }
}
