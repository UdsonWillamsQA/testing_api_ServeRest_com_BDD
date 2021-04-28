import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:build/reports/feature.html"},
        features = {"src/test/java/resources/features"}
)
public class CucumberRunner {

}