package testRunner;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features/",
    glue = {"stepdefinitions","hooks"},
    plugin = {"pretty", 
              "json:target/cucumber-report.json",
              "html:target/cucumber-html-report",
              "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
            },
    monochrome = true,
    dryRun = false                         // Skip execution of glue code
    //parallel = true  // Enable parallel execution of scenarios
)

public class TestRunner extends AbstractTestNGCucumberTests {
        //@DataProvider(parallel = true) enables parallel execution of scenarios in Cucumber with TestNG.
                @Override
                @DataProvider(parallel = true)
                public Object[][] scenarios() {
                    return super.scenarios();
            }
}
