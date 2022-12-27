package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets =CucumberOptions.SnippetType.CAMELCASE,
        publish = true,
        glue={"stepdefinition.generateAuthorization"},
        features = {"src/test/resources/features/generateAuthorization/generateAuthorization.feature"}

)
public class GenerateTokenTest {
}
