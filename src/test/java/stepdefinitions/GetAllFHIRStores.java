package stepdefinitions;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import io.restassured.http.ContentType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import testData.TestData;
import utilities.ExcelReader;
import static io.restassured.RestAssured.given;

public class GetAllFHIRStores{
    static Logger loggerload = LogManager.getLogger(GetAllFHIRStores.class);
    Response response;

    @When("A Get request is made to the API endpoint having projectId, location, datasetId.")
    public void a_get_request_is_made_to_the_api_endpoint_having_project_id_location_dataset_id() {
        response = given()
            .log().all()  // Print request details for debugging
            .auth()
            .oauth2(TestData.accessToken)  // Use OAuth2 token for authentication
            .contentType(ContentType.JSON)
            .when()
            .get("projects/" + TestData.projectId + "/locations/" + TestData.location + "/datasets/" + TestData.datasetId +"/fhirStores")
            .then().log().all().extract().response();
            }

            @Then("The response body and status code are printed out for validation in Get.")
            public void the_response_body_and_status_code_are_printed_out_for_validation_in_get() {
                if (this.response == null) {
                    System.out.println("Cannot validate response because it is null.");
                    loggerload.error("Cannot validate response because it is null.");
                    return;
                }
            // Print response for debugging
            int statusCode = response.getStatusCode();
            switch (statusCode) {
                case 200:
                System.out.println("All Stores are listed successfully.");
                loggerload.info("All Stores are listed successfully.");
                loggerload.info("Response Body: " + this.response.getBody().asString());
                System.out.println("Response Body: " + this.response.getBody().asString());
                System.out.println("Status Code: " + this.response.getStatusCode());
            }
    }
}