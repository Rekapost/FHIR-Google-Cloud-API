package testRunner;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {
                "src/test/resources/features/01_create-dataset.feature",
                "src/test/resources/features/02_update-patch.feature",
                "src/test/resources/features/03_CreateFHIRStore.feature",
                "src/test/resources/features/04_GetAllFHIRStores.feature"
            },
    glue = {"stepdefinitions","hooks"},
    plugin = {"pretty", 
              "html:target/cucumber-html-report",   // Generates HTML report
              "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
            },
    monochrome = true,
    dryRun = false                         // Skip execution of glue code
    //parallel = true  // Enable parallel execution of scenarios
)

public class TestRunner extends AbstractTestNGCucumberTests {
        //@DataProvider(parallel = true) enables parallel execution of scenarios in Cucumber with TestNG.
                @Override
                @DataProvider(parallel = false)
                public Object[][] scenarios() {
                    return super.scenarios();
            }
}
