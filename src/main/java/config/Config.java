package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
  private final Properties properties;
  private static final String FILENAME = "config.properties"; 

  public Config() {
    properties = new Properties();
    try {
      InputStream stream = Config.class.getClassLoader().getResourceAsStream(FILENAME);
      properties.load(stream);
      stream.close();
    } catch (IOException e) {
      throw new RuntimeException("No se ha podido leer el archivo de configuracion");
    }
  }

  public String getConfig(String config) {
    return properties.getProperty(config);
  }

  //Only for testing purposes
  public void addProperty(String key, String value){
    properties.setProperty(key, value);
  }

}
