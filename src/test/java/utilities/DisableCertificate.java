package utilities;

import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class DisableCertificate {
	
	public void disableSslVerification() {
	    try {
	        SSLContext sslContext = SSLContext.getInstance("TLS");

	        // Create a trust manager that does not validate certificate chains
	        TrustManager[] trustAllCerts = new TrustManager[]{
	            new X509TrustManager() {
	                public X509Certificate[] getAcceptedIssuers() {
	                    return null;
	                }
	                public void checkClientTrusted(X509Certificate[] certs, String authType) {
	                }
	                public void checkServerTrusted(X509Certificate[] certs, String authType) {
	                }
	            }
	        };

	        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
	        SSLContext.setDefault(sslContext);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
