package utilities;
import java.io.IOException;
import java.util.Map;

public class BaseEnvironment {

	public static Map<String,Object> dataFromJsonFile;
	static {
		
		String env = System.getProperty("env") == null ? "qa" : System.getProperty("env");
		// ? then
		System.out.println("Environment Value   : "+env);
		try {
           dataFromJsonFile = utilities.JsonUtils.getJsonDataAsMap("/environments/" + env + "/ApiData.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
}
