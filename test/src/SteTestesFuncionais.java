import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Suite.SuiteClasses({
        cadastroRendimento.class,
})
@Categories.IncludeCategory(TesteFuncional.class)
@Categories.ExcludeCategory(TesteExececao.class)
public class SteTestesFuncionais {
}