package ssm_smbms.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//璇诲彇閰嶇疆鏂囦欢鐨勫伐鍏风被---鍗曚緥妯″紡
public class ConfigManager {
	private static ConfigManager configManager = new ConfigManager();
	private static Properties properties;

	private ConfigManager() {
		String configFile = "database.properties";
		properties = new Properties();
		InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
		try {
			properties.load(is);
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 鍏ㄥ眬璁块棶鐐�
	public static ConfigManager getInstance() {

		return configManager;
	}

	public String getValue(String key) {
		return properties.getProperty(key);

	}
}
