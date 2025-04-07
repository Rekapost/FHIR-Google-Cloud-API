Feature:Update TimeZone

Background: Setup API base URL
    Given Set the base URL for the Google Cloud Healthcare API.
    Then You need an OAuth2 token (accessToken) to authenticate the request.

@UpdateTimeZone
Scenario: Update dataset timezone
  When A PATCH request is made to update the dataset timezone to "UTC".
  Then The response body and status code are printed out for validation in patch.
