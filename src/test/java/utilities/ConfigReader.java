package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;


public class ConfigReader {
	Properties properties;    // creating object 
	//private final static String propertyFilePath = "./configuration/config.properties";    OR
	File propertyFilePath=new File("src/test/resources/config.properties");
	
	public ConfigReader()  {		
		try 
		{FileInputStream stream = new FileInputStream(propertyFilePath);  // to open file in read mode 
		properties = new Properties();    // creating object
		try {
			properties.load(stream);  // load config file at run time 
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Exception is :"+ e.getMessage());
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
	}
	}
	
	
	public String getToken() {
		String token = properties.getProperty("Token");
		if (token != null)		
			return token;
		else
			throw new RuntimeException("token not specified in the Configuration.properties file.");
	}
}
