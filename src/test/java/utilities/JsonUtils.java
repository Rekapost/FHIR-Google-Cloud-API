package utilities;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	//read json file as map we need jackson TypeReference
	//Jackson’s ObjectMapper, when using it without any custom POJO classes, to deserialize an arbitrary piece of JSON to a Java Map.
	//new TypeReference<Map<String, Object>>() {} means the map is no longer untyped.
/*	ObjectMapper objectMapper = new ObjectMapper();
	Map map = objectMapper.readValue(getJsonString(), Map.class);
*/
	//the use of {} in the syntax for declaring the new TypeReference. TypeReference is an abstract class. 
	//The {} provides an empty implementation via an anonymous class, without which you would get a compile-time error:
	private static ObjectMapper mapper= new ObjectMapper();
	public static Map<String, Object> getJsonDataAsMap(String jsonfilename) throws StreamReadException, IOException{		
		String path=System.getProperty("user.dir")+"/src/test/resources"+jsonfilename;
		Map<String,Object> data= mapper.readValue(new File(path), new TypeReference<Map<String,Object>>(){});	
		return data;		
	}	
}
