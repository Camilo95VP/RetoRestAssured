package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets =CucumberOptions.SnippetType.CAMELCASE,
        publish = true,
        glue={"stepdefinition.partialUpdateBooking"},
        features = {"src/test/resources/features/partialUpdateBooking/partialUpdateBooking.feature"}

)
public class PartialUpdateBookingTest {
}
