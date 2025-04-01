
package stepdefinitions;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import testData.TestData;
import utilities.ExcelReader;

public class CreateFHIRStore {
    private static final Logger loggerLoad = LogManager.getLogger(CreateFHIRStore.class);
    public  String baseuri;
    public String endpoint;
    public String accessToken;
    public Response response;
    public String requestBody;

    // To get Endpoint from Excel using ExcelReader
    public String getEndpoint(String sheetName, Integer rowNumber) throws IOException, InvalidFormatException {
        ExcelReader reader = new ExcelReader();  
        //healthcare-system-api/locations/us-central1/datasets/healthcare_data/fhirStores?fhirStoreId=1983     
        List<Map<String, String>> testData = reader.getData("src/test/resources/Endpoint/FHIRExcel.xlsx", "CreateStoreEndpoint");
        endpoint = testData.get(rowNumber).get("FHIRStoreEndpoint"); // Ensure exact column name
        loggerLoad.info("Endpoint"+ endpoint);
        return endpoint;
    }

    @Then("Set the requestBody with the dataset details including fhirVersion")
    public void setRequestBody() {
        requestBody = "{ \"version\": \"R4\" }";
    }

    @When("A POST request is made to the API endpoint having projectId, location, datasetId, fhirStoreId")
    public void sendPostRequest() throws IOException, InvalidFormatException {
        // Retrieve endpoint from Excel
        endpoint = getEndpoint("Endpoint", 0); // Assuming row 0 for now
       // /projects/healthcare-system-api/locations/us-central1/datasets/healthcare_data/fhirStores?fhirStoreId=Raja_85
       //endpoint = String.format("%s/projects/healthcare-system-api/locations/us-central1/datasets/healthcare_data/fhirStores?fhirStoreId=Raja_1985", baseuri);
       System.out.println("Final API URL: " + endpoint);
       loggerLoad.info("Final API URL: " + endpoint);
       
        //String apiUrl = String.format("%s/projects/YOUR_PROJECT_ID/locations/us-central1/datasets/YOUR_DATASET/fhirStores/YOUR_FHIR_STORE/fhir", baseuri);
        //https://healthcare.googleapis.com/v1/projects/healthcare-system-api/locations/us-central1/datasets/healthcare_data/fhirStores?fhirStoreId=Reka_83 
        baseuri = CommonStepDefinition.baseuri;
        accessToken = CommonStepDefinition.accessToken;
        response = utilities.Perform.performSendRequest(baseuri, endpoint, accessToken, new HashMap<>(), requestBody,"POST" );
    }

    @Then("The response body and status code are printed out for validation to create store.")
    public void validateResponse() {
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        loggerLoad.info("Response Status Code: " + response.statusCode());
        loggerLoad.info("Response Body: " + response.getBody().asString());

        // Validate response status
        Assert.assertEquals(200, response.getStatusCode()); // Expecting 201 Created
    }
}

/*
When you run mvn test, the request will:

Fetch the FHIR Store API endpoint from Excel.

Retrieve the OAuth2 access token.

Construct the FHIR Store request payload.

Send the POST request to create a FHIR Store in Google Cloud.

Print and log the response status code and response body.

Validate that the response returns 201 Created.
 */