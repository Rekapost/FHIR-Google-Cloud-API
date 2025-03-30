package testData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestData {

    public static String getAccessToken() throws IOException {
        Process process = Runtime.getRuntime().exec("gcloud auth application-default print-access-token");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return reader.readLine();
    }

    public static String projectId = "healthcare-system-api";  // Your Google Cloud Project ID
    public static String location = "us-central1";     // Supported location (e.g., us-central1)
    public static String datasetId = "healthcare_data";  // Your unique Dataset ID
    public static String accessToken = "ya29.a0AeXRPp4LiIUmlZMdnsf_f6Oa3jIldkEpyDdL2DXDdnogociwC6pFSVhq44TdH2lUofOXEMn6zpoBPg6dO8gHwzVppCMxeXjuLucOQwKYeukaxr8ysfId_nTDA7-YfEPCg6mM4f8bPXb-UmtJxDvLHZOm9gsoaKGJiwOAFTIlawaCgYKAboSARASFQHGX2MiQmEvnlVXQ4xHTEbKBmHjZA0177";
    //public static String accessToken = getAccessToken();

}