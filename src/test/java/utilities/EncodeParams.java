package utilities;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
/*   
$ is encoded as %24
? is encoded as %3F
space is encoded as %20
*/

public class EncodeParams {
	
	
	// Method to encode query parameters
    public String encodeParams(String url) throws UnsupportedEncodingException {
        if (url == null) {
            Loggerload.error("URL is null");
            return null;
        }
        String[] parts = url.split("\\?");
        if (parts.length > 1) {
            String baseUrl = parts[0];
            String query = parts[1];
            String[] queryParams = query.split("&");
            StringBuilder encodedQuery = new StringBuilder();
            for (String param : queryParams) {
                String[] keyValue = param.split("=");
                if (encodedQuery.length() > 0) {
                    encodedQuery.append("&");
                }
                encodedQuery.append(URLEncoder.encode(keyValue[0], "UTF-8"))
                            .append("=")
                            .append(URLEncoder.encode(keyValue[1], "UTF-8"));
            }
            return baseUrl + "?" + encodedQuery.toString();
        } else {
            return url;
        }
    }
} 
    
    
//	// Method to encode query parameters
//    public String encodeParams(String url) throws UnsupportedEncodingException {
//       
//    	if (url == null) {
//            LoggerUtility.error("URL is null");
//            return null;
//        } 	
//
//    	 // Split URL into base URL and query parameters
//        String[] parts = url.split("\\?", 2); // Limit to 2 parts to avoid issues with multiple '?' in URL
//        if (parts.length > 1) {
//            String baseUrl = parts[0];
//            String query = parts[1];
//            String[] queryParams = query.split("&");
//            StringBuilder encodedQuery = new StringBuilder();
//
//            // Encode each query parameter
//            for (String param : queryParams) {
//                String[] keyValue = param.split("=", 2); // Limit to 2 parts to avoid issues with '=' in values
//                if (keyValue.length == 2) {
//                    String encodedKey = URLEncoder.encode(keyValue[0], StandardCharsets.UTF_8.toString());
//                    String encodedValue = URLEncoder.encode(keyValue[1], StandardCharsets.UTF_8.toString());
//                    if (encodedQuery.length() > 0) {
//                        encodedQuery.append("&");
//                    }
//                    encodedQuery.append(encodedKey).append("=").append(encodedValue);
//                } else {
//                    // Handle case where there is no '=' in the param, e.g., $filter=id eq 1021041
//                    String encodedParam = URLEncoder.encode(param, StandardCharsets.UTF_8.toString());
//                    if (encodedQuery.length() > 0) {
//                        encodedQuery.append("&");
//                    }
//                    encodedQuery.append(encodedParam);
//                }
//            }
//            return baseUrl + "?" + encodedQuery.toString().replace(" ", "%20");
//        } else {
//            return url.replace(" ", "%20"); // No query parameters to encode
//        }
//    }
//}	
//	

 /*   // Method to encode query parameters
    public String encodeParams(String url) throws UnsupportedEncodingException  {
        if (url == null) {
            LoggerUtility.error("URL is null");
            return null;
        }
        String[] parts = url.split("\\?");
        if (parts.length > 1) {
            String baseUrl = parts[0];
            String query = parts[1];
            String[] queryParams = query.split("&");
            StringBuilder encodedQuery = new StringBuilder();
            for (String param : queryParams) {
                String[] keyValue = param.split("=");
                if (encodedQuery.length() > 0) {
                    encodedQuery.append("&");
                }
                encodedQuery.append(URLEncoder.encode(keyValue[0], "UTF-8"))
                            .append("=")
                            .append(URLEncoder.encode(keyValue[1], "UTF-8"));
            }
            return baseUrl + "?" + encodedQuery.toString();
        } else {
            return url;
        }
    }
}
*/
	
/*	// Method to encode query parameters
    public String encodeParams(String url) throws UnsupportedEncodingException {
    	LoggerUtility.info("Excel Endpoint : "+ url);
        if (url == null) {
            LoggerUtility.error("URL is null");
            return null;
        }
        String[] parts = url.split("\\?");
        if (parts.length > 1) {
            String baseUrl = parts[0];
            String query = parts[1];
            String[] queryParams = query.split("&");
            StringBuilder encodedQuery = new StringBuilder();
            for (String param : queryParams) {
                String[] keyValue = param.split("=");
                if (encodedQuery.length() > 0) {
                    encodedQuery.append("&");
                }
                encodedQuery.append(URLEncoder.encode(keyValue[0], "UTF-8"))
                            .append("=")
                            .append(URLEncoder.encode(keyValue[1], "UTF-8"));
            }
            return baseUrl + "?" + encodedQuery.toString();
        } else {
            return url;
        }
    }
}
*/
