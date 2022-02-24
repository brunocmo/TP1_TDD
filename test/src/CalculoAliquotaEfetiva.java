import com.unb.CalculoIRPF;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CalculoAliquotaEfetiva {

    private CalculoIRPF simulador;

    @Before
    public void setup(){
        simulador = new CalculoIRPF();
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcAliquotaEfetivaValorAlto() {
        simulador.cadastrarRendimento("Sálario", 10000f);
        simulador.cadastrarPrevidenciaOficial("Contribuicao Compulsoria", 800f);
        simulador.cadastrarDependentes("Julio Cesar", "17/07/2017");
        simulador.cadastrarPensaoAlimenticia(2000f);
        simulador.cadastrarOutrasDeducoes("Previdência Privada", 1000f);
        simulador.calcularImposto();

        Assert.assertEquals( 0.0783f, simulador.getCalculoAliquotaEfetiva(), 0.0001f );
    }

    @Test
    @Category(TesteFuncional.class)
    public void calcAliquotaEfetivaValorBaixo() {
        simulador.cadastrarRendimento("Alugel", 3200f);
        simulador.cadastrarPrevidenciaOficial("Previdencia oficial", 400f);
        simulador.cadastrarDependentes("Ricardo", "13/02/2021");
        simulador.cadastrarDependentes("Letícia", "02/08/2017");
        simulador.cadastrarDependentes("João", "30/04/2016");
        simulador.cadastrarPensaoAlimenticia(0f);
        simulador.cadastrarOutrasDeducoes("FAPI", 200f);
        simulador.calcularImposto();

        Assert.assertEquals( 0.0029f, simulador.getCalculoAliquotaEfetiva(), 0.0001f );
    }
}
