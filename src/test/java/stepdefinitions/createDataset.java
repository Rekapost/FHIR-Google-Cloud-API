package stepdefinitions;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utilities.ExcelReader;

public class CreateDataset {
    String requestBody;
    Response response; 
    public String baseuri;
    public String endpoint;
    public String accessToken;  
    private static final Logger loggerload = LogManager.getLogger(CreateDataset.class);

     // To get Endpoint from Excel using ExcelReader
     public String getEndpoint(String sheetName, Integer rowNumber) throws IOException, InvalidFormatException {
        ExcelReader reader = new ExcelReader();   
        List<Map<String, String>> testData = reader.getData("src/test/resources/Endpoint/FHIRExcel.xlsx", "CreateDataSetEndpoint");
        endpoint = testData.get(rowNumber).get("FHIRDataSetEndpoint"); // Ensure exact column name
        loggerload.info("Endpoint"+ endpoint);
        return endpoint;
    }

   // Check if the dataset exists and delete if needed
   public boolean datasetExists(String datasetId) {
    // Make a GET request to check if the dataset exists
    Response getResponse = utilities.Perform.performSendRequest(baseuri, endpoint + "/" + datasetId, accessToken, new HashMap<>(), "", "GET");
    return getResponse.getStatusCode() == 200;
    }

    // Delete the existing dataset
    public void deleteDataset(String datasetId) {
        Response deleteResponse = utilities.Perform.performSendRequest(baseuri, endpoint + "/" + datasetId, accessToken, new HashMap<>(), "", "DELETE");
        if (deleteResponse.getStatusCode() == 200) {
            loggerload.info("Dataset deleted successfully.");
        } else {
            loggerload.error("Failed to delete the existing dataset.");
        }
    }

    // Generate unique dataset ID
    public String generateUniqueDatasetId() {
        return "dataset-" + UUID.randomUUID().toString();
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

         // Define the payload for creating a dataset with a unique ID
         String uniqueDatasetId = generateUniqueDatasetId();
         requestBody = "{\n" +
                       "  \"datasetId\": \"" + uniqueDatasetId + "\",\n" +
                       //"  \"displayName\": \"" + string2 + "\",\n" +
                       //"  \"description\": \"" + string3 + "\",\n" +
                       //"  \"timeZone\": \"" + string4 + "\"\n" +
                       "}";
        loggerload.info("Request Body: " + requestBody);
        System.out.println("Request Body: " + requestBody);

        //requestBody = "{}"; // No fields required in the request body
    }

    @When("A POST request is made to the API endpoint: \\/projects\\/\\{projectId}\\/locations\\/\\{location}\\/datasets.")
    public void a_post_request_is_made_to_the_api_endpoint_projects_locations_datasets() throws IOException, InvalidFormatException {
        loggerload.info("Make POST request to create dataset ");
        /*// Make POST request to create dataset      
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
        */
        baseuri = CommonStepDefinition.baseuri;
        accessToken = CommonStepDefinition.accessToken;
        endpoint = getEndpoint("Endpoint", 0); // Assuming row 0 for now
        System.out.println("Final API URL: " + endpoint);
        loggerload.info("Final API URL: " + endpoint);

    /*    String datasetId = "healthcare_data";  
        // Step 1: Check if dataset exists
        if (datasetExists(datasetId)) {
            loggerload.info("Dataset already exists. Deleting it.");
            deleteDataset(datasetId); // Delete the existing dataset
        }
    */
        // Step 2: Create the new dataset
        response = utilities.Perform.performSendRequest(baseuri, endpoint, accessToken, new HashMap<>(), requestBody, "POST");

    }    
    
    @Then("The response body and status code are printed out for validation.")
    public void the_response_body_and_status_code_are_printed_out_for_validation() {
       // Print response for debugging
       int statusCode = response.getStatusCode();
        switch (statusCode) { 
            case 201:
                System.out.println("Dataset created successfully.");
                loggerload.info("Dataset created successfully.");
                loggerload.info("Response Body: " + response.getBody().asString());
                System.out.println("Response: " + response.getBody().asString());
                System.out.println("Status Code: " + response.getStatusCode());
                break;
            case 403:
                System.out.println("Insufficient permissions to create a dataset.");
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
