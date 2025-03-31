Feature: Create a FHIR Store
Scenario: Create a FHIR Store
Given Set the base URL for the Google Cloud Healthcare API to create store.
Then You need an OAuth2 token (accessToken) to authenticate the request to create store. 
Then Set the requestBody with the dataset details including fhirVersion
When A POST request is made to the API endpoint having projectId, location, datasetId, fhirStoreId
Then The response body and status code are printed out for validation to create store.

# https://healthcare.googleapis.com/v1/projects/YOUR_PROJECT_ID/locations/us-central1/datasets/YOUR_DATASET/fhirStores/YOUR_FHIR_STORE/fhir
