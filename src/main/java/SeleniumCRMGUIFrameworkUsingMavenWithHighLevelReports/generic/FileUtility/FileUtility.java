package SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.FileUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {

	public String getDataFromPropertiesFile(String key) throws Throwable {
		// read common data from Properties file
		// step 1 : Get the java representation object of the physical file
		FileInputStream fis = new FileInputStream("./src/test/resources/commondata.properties");
		// step 2 : Create a Object Of properties class
		Properties pObj = new Properties();
		// step 3 : Load All the key-value pairs from fis to properties object using
		// load method
		pObj.load(fis);
		// step 4 : Get the value based on the key
		String data = pObj.getProperty(key);
		return data;
	}
}
