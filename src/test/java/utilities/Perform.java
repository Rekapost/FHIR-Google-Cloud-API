package utilities;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import reporting.ExtentReportManager;

public class Perform {
	private static RequestSpecification getRequestSpecification(String baseUri, String endpoint, String token, Object payload, Map<String, String> headers, String method) {
		// Build the request specification
		RequestSpecification requestSpec = RestAssured						
			.given()
			.baseUri(baseUri)
			.basePath(endpoint)  // Ensure the endpoint is added
			.auth()
			.oauth2(token)
			.contentType(ContentType.JSON);	
		
		// Add headers if present
		if (headers != null && !headers.isEmpty()) {
			requestSpec.headers(headers);  // Add the custom headers to the request
		}
		return requestSpec;
	}

	public static Response performSendRequest(String baseUri, String endpoint, String token, Map<String, String> headers, Object payload, String method) {
		// Get the request specification using the helper method
		RequestSpecification requestSpecification = getRequestSpecification(baseUri, endpoint, token,payload, headers, method);
	  
		// Add payload only for POST and PUT methods
		if (payload != null && (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT"))) {
			requestSpecification.body(payload);
		}

		Response response;
		switch (method.toUpperCase()) {
			case "POST":
				response = requestSpecification.post(baseUri + endpoint);
				break;
			case "GET":
				response = requestSpecification.get();
				break;
			case "PUT":
				response = requestSpecification.put();
				break;
			case "PATCH":
				response = requestSpecification.patch(baseUri + endpoint);
				break;	
			case "DELETE":
				response = requestSpecification.delete();
				break;
			default:
				throw new IllegalArgumentException("Invalid HTTP method: " + method);
		}

		// Optionally, log the request and response for debugging
		printRequestLogInReport(requestSpecification);
		printResponsetLogInReport(response);
	
		// Return the response
		return response;
	}
	 
	 private static void printRequestLogInReport(RequestSpecification requestSpecification) {
			QueryableRequestSpecification queryableRequestSpecification= SpecificationQuerier.query(requestSpecification);
			ExtentReportManager.logInfoDetails("Baseuri is " + queryableRequestSpecification.getBaseUri());
			ExtentReportManager.logInfoDetails("Endpoint is " + queryableRequestSpecification.getBasePath());
	        ExtentReportManager.logInfoDetails("Method is " + queryableRequestSpecification.getMethod());
	        ExtentReportManager.logInfoDetails("Headers are ");
	        //ExtentReportManager.logInfoDetails("Headers are "+ queryableRequestSpecification.getHeaders().asList().toString());
	        ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
	        ExtentReportManager.logInfoDetails("Request body is  : ");
	        ExtentReportManager.logJson(queryableRequestSpecification.getBody());
			System.out.println("Request Details: " + requestSpecification.log().all());
	        //ExtentReportManager.logJson(queryableRequestSpecification.getBody());
		}
		
		private static void printResponsetLogInReport(Response response) {
			
	        ExtentReportManager.logInfoDetails("Response Status is " + response.getStatusCode());
	        ExtentReportManager.logInfoDetails("Headers are ");
	       // ExtentReportManager.logInfoDetails("Response Headers are "+response.getHeaders() .asList().toString());
	        ExtentReportManager.logHeaders(response.getHeaders() .asList());
	        ExtentReportManager.logInfoDetails("Response body is  : ");
	        ExtentReportManager.logJson(response.getBody().prettyPrint());
			System.out.println("Response Details: " + response.prettyPrint());
		}	
}
