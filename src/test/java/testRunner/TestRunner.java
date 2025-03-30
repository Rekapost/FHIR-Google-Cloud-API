package testRunner;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinitions","hooks"},
    plugin = {"pretty", 
              "json:target/cucumber-report.json",
              "html:target/cucumber-html-report",
              "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
    monochrome = true
    //parallel = true  // Enable parallel execution of scenarios
)

public class TestRunner extends AbstractTestNGCucumberTests {
}
