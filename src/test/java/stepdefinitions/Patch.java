package stepdefinitions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testData.TestData;

public class Patch {
static Logger loggerload = LogManager.getLogger(Patch.class);
Response response;  
public static String location = "us-central2";  

@When("A PATCH request is made to update the dataset timezone to {string}.")
public void a_patch_request_is_made_to_update_the_dataset_timezone(String timeZone) {
    String requestUrl;
    String updateRequestBody = "{ \"timeZone\": \"America/Chicago\" }";

    loggerload.info("Executing PATCH request for dataset timezone update...");
    System.out.println("Executing PATCH request for dataset timezone update...");
    System.out.println("Request Body: " + updateRequestBody);
    System.out.println("OAuth2 Token: " + (TestData.accessToken != null ? "Token Present" : "Token is NULL"));
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

        // Validate response before accessing it
        if (this.response == null) {
            throw new IllegalStateException("Response is null. API request might have failed.");
            } else {
                System.out.println("Status Code: " + response.getStatusCode());
            }
       

        int statusCode = this.response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        loggerload.info("Status Code: " + statusCode);
     
        if (statusCode == 200 || statusCode == 201) {
            System.out.println("Dataset timezone updated successfully.");
            loggerload.info("Dataset timezone updated successfully.");
        } else {
            System.err.println("Error updating dataset: " + this.response.getBody().asString());
            loggerload.error("Error updating dataset: " + this.response.getBody().asString());
        }
    } catch (Exception e) {
        System.err.println("Exception occurred during PATCH request: " + e.getMessage());
        loggerload.error("Exception occurred during PATCH request", e);
    }
}
@Then("The response body and status code are printed out for validation in patch.")
public void the_response_body_and_status_code_are_printed_out_for_validation_in_patch() {
    if (this.response == null) {
        System.err.println("Cannot validate response because it is null.");
        loggerload.error("Cannot validate response because it is null.");
        return;
    }


    loggerload.info("Response Body: " + response.getBody().asString());
    System.out.println("Response Body: " + this.response.getBody().asString());
    System.out.println("Status Code: " + this.response.getStatusCode());
}

}
