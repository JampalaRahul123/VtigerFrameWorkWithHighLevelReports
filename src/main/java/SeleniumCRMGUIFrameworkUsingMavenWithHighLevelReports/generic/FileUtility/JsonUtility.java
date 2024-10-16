package SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.FileUtility;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	public String getDataFromJsonFile(String key) throws ParseException,Throwable {
		// read common data from Json file
		// Step 1 : Parse JSON physical file into Java Object using JSonParse Class
		FileReader fileR = new FileReader("./src/test/resources/AppCommonData.json");
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fileR);
		// JSON Object is stored in Java Object Class by creating object of object class
		// Step 2 : convert java object into JSON object using down casting
		JSONObject map = (JSONObject) obj;
		// Step 3 : Get The Value from JSON file using key
		String data = (String)map.get(key);
		return data;
	}
}
