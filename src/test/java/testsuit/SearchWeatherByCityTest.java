package testsuit;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "classpath:features/searchWeatherByCity.feature",
        plugin = {"pretty"})
public class SearchWeatherByCityTest {
}
