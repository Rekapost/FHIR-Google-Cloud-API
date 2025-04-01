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
    public  String baseuri;
    public String endpoint;
    public String accessToken;
    public Response response;
    public String requestBody;

        // To get Endpoint from Excel using ExcelReader
        public String getEndpoint(String sheetName, Integer rowNumber) throws IOException, InvalidFormatException {
            ExcelReader reader = new ExcelReader();  
            //healthcare-system-api/locations/us-central1/datasets/healthcare_data/fhirStores?fhirStoreId=1983     
            List<Map<String, String>> testData = reader.getData("src/test/resources/Endpoint/FHIRExcel.xlsx", "GetAllFHIRStoresEndpoint");
            endpoint = testData.get(rowNumber).get("FHIRStoresEndpoint"); // Ensure exact column name
            loggerload.info("Endpoint"+ endpoint);
            return endpoint;
        }
    @When("A Get request is made to the API endpoint having projectId, location, datasetId.")
    public void a_get_request_is_made_to_the_api_endpoint_having_project_id_location_dataset_id() {
        /*  response = given()
            .log().all()  // Print request details for debugging
            .auth()
            .oauth2(TestData.accessToken)  // Use OAuth2 token for authentication
            .contentType(ContentType.JSON)
            .when()
            .get("projects/" + TestData.projectId + "/locations/" + TestData.location + "/datasets/" + TestData.datasetId +"/fhirStores")
            .then().log().all().extract().response();
            }
        */

        try {
            endpoint = getEndpoint("Endpoint", 0); // Assuming row 0 for now
            System.out.println("Final API URL: " + endpoint);
            loggerload.info("Final API URL: " + endpoint);
            baseuri = CommonStepDefinition.baseuri;
            accessToken = CommonStepDefinition.accessToken;
            response = utilities.Perform.performSendRequest(baseuri, endpoint, accessToken, new HashMap<>(), null,"GET" );
        } catch (IOException | InvalidFormatException e) {
            loggerload.error("Error occurred while getting the endpoint: " + e.getMessage());
            
        }
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
/*
 * // GET Request Example
response = Perform.sendRequest(baseuri, endpoint, accessToken, new HashMap<>(), null, "GET");

// DELETE Request Example
response = Perform.sendRequest(baseuri, endpoint, accessToken, new HashMap<>(), null, "DELETE");

 */