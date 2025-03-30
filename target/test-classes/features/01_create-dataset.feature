Feature: Create DataSet

Scenario:Create a new dataset
Given Set the base URL for the Google Cloud Healthcare API.
Then You need an OAuth2 token (accessToken) to authenticate the request. 
Then The requestBody of the dataset having "<unique-identifier>","<name>","<description>","<timezone>".
When A POST request is made to the API endpoint: /projects/{projectId}/locations/{location}/datasets.
Then The response body and status code are printed out for validation.



# Before making the request, ensure you have an OAuth2 access token with the appropriate scopes (e.g., https://www.googleapis.com/auth/cloud-platform) to access Google Cloud Healthcare API.
# You can use Google’s OAuth2 client libraries or Google Cloud SDK to generate an access token. Here's an example using the Google Cloud SDK to generate the token:
# gcloud auth application-default print-access-token