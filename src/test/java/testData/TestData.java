package testData;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestData {

    // Method to get the Google Cloud authentication token
    public static String getAccessToken() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                "C:\\Program Files (x86)\\Google\\Cloud SDK\\google-cloud-sdk\\bin\\gcloud.cmd", 
                "auth", "print-access-token"
            );
            
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Read the output of the command
            StringBuilder token = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    token.append(line);
                }
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();
            if (exitCode != 0 || token.toString().trim().isEmpty()) {
                throw new RuntimeException("Failed to retrieve access token. Exit code: " + exitCode);
            }

            return token.toString().trim(); // Return the access token without spaces
        } catch (Exception e) {
            throw new RuntimeException("Error getting access token", e);
        }
    }

    // Google Cloud Project details
    public static String projectId = "healthcare-system-api";  // Your Google Cloud Project ID
    public static String location = "us-central1";     // Supported location (e.g., us-central1)
    public static String datasetId = "healthcare_data";  // Your unique Dataset ID
    public static String accessToken ="ya29.a0AeXRPp6uHTmmDT96Q3bHO1d7meqtGYeuIyVWXCQqVB3s_chXjLsn2HI5KySjtl41LDfR5sErksnai5fZ6yxc5nSCjm9tcWpLsTfDHX_bsDp4ecikWt5uKqJQtN2UAe2c7sTOFsFNoPxNRhBqePrkyyGGzEHapnqPEwcmsKe0xQaCgYKARQSARASFQHGX2MiMs1TJsTeolA-S0-L-NDvdQ0177";
    // Fetch token dynamically when needed instead of storing it statically
}

 /*  public static String getAccessToken() throws IOException {
        Process process = Runtime.getRuntime().exec("gcloud auth application-default print-access-token");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return reader.readLine();
    }
  */