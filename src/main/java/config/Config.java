package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {
  private Properties properties;
  private static Config INSTANCE;

  private Config() throws IOException {
    properties = new Properties();
    final InputStream stream = Config.class.getClassLoader().getResourceAsStream("config.properties");
    properties.load(stream);
  }

  public static Config getInstance() {
    try {
      if (INSTANCE == null) {
        INSTANCE = new Config();
      }
      return INSTANCE;
    } catch(IOException exception) {
      throw new RuntimeException("Error trying to open config file");
    }
  }

  public String getConfig(String config) {
    return properties.getProperty(config);
  }
}
