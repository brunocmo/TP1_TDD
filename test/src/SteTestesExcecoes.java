import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Suite.SuiteClasses({
        cadastroRendimentoTestFuncionais.class,
        cadastroRendimentoTestExcecoes.class
})
@Categories.IncludeCategory(TesteExececao.class)

public class SteTestesExcecoes {
}
