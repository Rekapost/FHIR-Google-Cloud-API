package stepdefinitions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testData.TestData;

public class createDataset {
    String requestBody;
    Response response;   
    private static final Logger loggerload = LogManager.getLogger(createDataset.class);
    
    @Given("Set the base URL for the Google Cloud Healthcare API.")
    public void set_the_base_url_for_the_google_cloud_healthcare_api() {
        // Set base URL for the Google Cloud Healthcare API
        String baseURL = "https://healthcare.googleapis.com/v1/";
        
        // Set base URL for REST Assured
        RestAssured.baseURI = baseURL;
    }
    @Then("You need an OAuth2 token \\(accessToken) to authenticate the request.")
    public void you_need_an_o_auth2_token_access_token_to_authenticate_the_request() {
        //try {
            //accessToken = getAccessToken();
            System.out.println("Access token: " + TestData.accessToken);
            loggerload.info("Access token: " + TestData.accessToken);
        //} catch (IOException e) {
        //    System.err.println("Failed to retrieve access token: " + e.getMessage());
            //e.printStackTrace();
        //}
    }

    @Then("The requestBody of the dataset having {string},{string},{string},{string}.")
    public void the_request_body_of_the_dataset_having(String string, String string2, String string3, String string4) {
    // Define the payload for creating a dataset (adjust fields based on API documentation)
          /*requestBody = "{\n" +
            "  \"datasetId\": \"" + TestData.datasetId + "\",\n" +
            "  \"displayName\": \"Test Dataset\",\n" +
            "  \"description\": \"Healthcare dataset\",\n" +
            "  \"timeZone\": \"us-central1\"\n" +
            "}";
        */
        requestBody = "{}"; // No fields required in the request body
    }
    @When("A POST request is made to the API endpoint: \\/projects\\/\\{projectId}\\/locations\\/\\{location}\\/datasets.")
    public void a_post_request_is_made_to_the_api_endpoint_projects_locations_datasets() {
        loggerload.info("Make POST request to create dataset ");
        // Make POST request to create dataset      
            response = given()
            .log().all()  // Print request details for debugging
            .auth()
            .oauth2(TestData.accessToken)  // Use OAuth2 token for authentication
            .contentType(ContentType.JSON)
            .body(requestBody)  // Empty body
            .when()
            .post("projects/" + TestData.projectId + "/locations/" + TestData.location + "/datasets?datasetId=" + TestData.datasetId)
            .then().log().all().extract().response();
            }

    @Then("The response body and status code are printed out for validation.")
    public void the_response_body_and_status_code_are_printed_out_for_validation() {
       // Print response for debugging
       int statusCode = response.getStatusCode();
        switch (statusCode) {
            case 200:
                System.out.println("All Stores are listed successfully.");
                loggerload.info("All Stores are listed successfully.");
            case 201:
                System.out.println("Dataset created successfully.");
                loggerload.info("Dataset created successfully.");
                loggerload.info("Response Body: " + response.getBody().asString());
                System.out.println("Response: " + response.getBody().asString());
                System.out.println("Status Code: " + response.getStatusCode());
                break;
            case 409:
                System.out.println("Dataset already exists. Skipping creation.");
                loggerload.info("Dataset already exists. Skipping creation.");
                break;
            default:
                System.err.println("Error: " + response.getBody().asString());
                break;
            }
        }
    }
    
//https://healthcare.googleapis.com/v1/projects/healthcare-system-api/locations/us-central1/datasets?datasetId=healthcare_data
