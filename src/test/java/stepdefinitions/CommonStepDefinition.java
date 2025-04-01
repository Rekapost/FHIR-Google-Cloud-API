package stepdefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import testData.TestData;
import utilities.JsonUtils;
import java.io.IOException;
import java.util.Map;
import testData.TestData;

public class CommonStepDefinition{
    private static final Logger loggerload = LogManager.getLogger(CommonStepDefinition.class);
    static String baseuri;
    static String accessToken;

        @Given("Set the base URL for the Google Cloud Healthcare API.")
        public void set_the_base_url_for_the_google_cloud_healthcare_api() throws IOException {
            // Determine environment
            String env = System.getProperty("env") == null ? "qa" : System.getProperty("env");
            System.out.println("Environment Value: " + env);
            loggerload.info("Environment: " + env);

            // Get base URI from JSON configuration
            Map<String, Object> data = utilities.JsonUtils.getJsonDataAsMap("/environments/" + env + "/ApiData.json");
            baseuri = (String) data.get("createQAEndpoint");

            System.out.println("Baseuri: " + baseuri);
            loggerload.info("BaseURI: " + baseuri);
            //"https://healthcare.googleapis.com/v1"

            if (baseuri == null) {
                loggerload.error("baseuri is null");
                throw new RuntimeException("Base URI is not set");
            }
        }

        @Then("You need an OAuth2 token \\(accessToken) to authenticate the request.")
        public void you_need_an_o_auth2_token_access_token_to_authenticate_the_request() {
                accessToken = TestData.accessToken;
                loggerload.info("Access token: " + TestData.accessToken); 
        }

}