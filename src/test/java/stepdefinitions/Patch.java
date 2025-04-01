package stepdefinitions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testData.TestData;
import utilities.ExcelReader;
public class Patch {
    static Logger loggerload = LogManager.getLogger(Patch.class);
    public  String baseuri;
    public String endpoint;
    public String accessToken;
    public Response response;
    public String requestBody;  
    public static String location = "us-central2";  

        // To get Endpoint from Excel using ExcelReader
        public String getEndpoint(String sheetName, Integer rowNumber) throws IOException, InvalidFormatException {
            ExcelReader reader = new ExcelReader();  
            //healthcare-system-api/locations/us-central1/datasets/healthcare_data/fhirStores?fhirStoreId=1983     
            List<Map<String, String>> testData = reader.getData("src/test/resources/Endpoint/FHIRExcel.xlsx", "UpdateTimeZoneEndpoint");
            endpoint = testData.get(rowNumber).get("FHIRUpdateDatasetEndpoint"); // Ensure exact column name
            loggerload.info("Endpoint"+ endpoint);
            return endpoint;
        }

@When("A PATCH request is made to update the dataset timezone to {string}.")
public void a_patch_request_is_made_to_update_the_dataset_timezone(String timeZone) {
    //String requestUrl;
    String updateRequestBody = "{ \"timeZone\": \"America/Chicago\" }";

    loggerload.info("Executing PATCH request for dataset timezone update...");
    System.out.println("Executing PATCH request for dataset timezone update...");
    System.out.println("Request Body: " + updateRequestBody);

    /*    System.out.println("OAuth2 Token: " + (TestData.accessToken != null ? "Token Present" : "Token is NULL"));
    //String requestUrl = "https://healthcare.googleapis.com/v1/projects/healthcare-system-api/locations/us-central1/datasets/healthcare_data?updateMask=timeZone";
    requestUrl = "https://healthcare.googleapis.com/v1/projects/" 
        + TestData.projectId + "/locations/" 
        + TestData.location + "/datasets/" 
        + TestData.datasetId + "?updateMask=timeZone";

    try {
        // Assign response before checking its status
        this.response = given()
            .auth()
            //.header("Authorization", TestData.accessToken)
            .oauth2(TestData.accessToken)
            .contentType(ContentType.JSON)
            .body(updateRequestBody)
            .when()
            .patch(requestUrl)
            .then()
            .log().all()
            .extract()
            .response();
    */

        baseuri = CommonStepDefinition.baseuri;
        accessToken = CommonStepDefinition.accessToken;
        try {
            endpoint = getEndpoint("Endpoint", 0); // Assuming row 0 for now
            System.out.println("Final API URL: " + endpoint);
            loggerload.info("Final API URL: " + endpoint);
        } catch (IOException | InvalidFormatException e) {
            System.err.println("Error retrieving endpoint: " + e.getMessage());
            loggerload.error("Error retrieving endpoint", e);
            throw new RuntimeException("Failed to retrieve endpoint", e);
        }
        response = utilities.Perform.performSendRequest(baseuri, endpoint, accessToken, new HashMap<>(), updateRequestBody,"PATCH" );
          
        // Validate response before accessing it
        if (response == null) {
            throw new IllegalStateException("Response is null. API request might have failed.");
            } else {
                System.out.println("Status Code: " + response.getStatusCode());
            }

        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        loggerload.info("Status Code: " + statusCode);
     
        if (statusCode == 200 || statusCode == 201) {
            System.out.println("Dataset timezone updated successfully.");
            loggerload.info("Dataset timezone updated successfully.");
        } else {
            System.err.println("Error updating dataset: " + response.getBody().asString());
            loggerload.error("Error updating dataset: " + response.getBody().asString());
        }
        /*} catch (Exception e) {
            System.err.println("Exception occurred during PATCH request: " + e.getMessage());
            loggerload.error("Exception occurred during PATCH request", e);
        }
        */
    }
@Then("The response body and status code are printed out for validation in patch.")
public void the_response_body_and_status_code_are_printed_out_for_validation_in_patch() {
    if (response == null) {
        System.err.println("Cannot validate response because it is null.");
        loggerload.error("Cannot validate response because it is null.");
        return;
    }

    loggerload.info("Response Body: " + response.getBody().asString());
    System.out.println("Response Body: " + response.getBody().asString());
    System.out.println("Status Code: " + response.getStatusCode());
}

}
