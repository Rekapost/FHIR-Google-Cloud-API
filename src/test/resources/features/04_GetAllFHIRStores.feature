Feature: Get all FHIR Stores

Background: Setup API base URL
    Given Set the base URL for the Google Cloud Healthcare API.
    Then You need an OAuth2 token (accessToken) to authenticate the request.

@GetAllFHIRStores
Scenario: Get all FHIR Stores
    When A Get request is made to the API endpoint having projectId, location, datasetId. 
    Then The response body and status code are printed out for validation in Get.