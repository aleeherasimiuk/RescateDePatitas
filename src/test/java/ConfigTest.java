import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import config.Config;
public class ConfigTest {

  Config config;

  @BeforeEach
  void setUp(){
    config = new Config();

    config.addProperty("api.refugio.url", "https://example.com/");
    config.addProperty("mail.sender", "example@gmail.com");
    config.addProperty("api.refugio.token", "Bearer <TOKEN>");
    config.addProperty("mail.password", "hackme");
  }

  @Test
  void testURL(){
    String url = config.getConfig("api.refugio.url");
    assertEquals("https://example.com/", url);
  }

  @Test
  void testEmail(){
    assertEquals("example@gmail.com", config.getConfig("mail.sender"));
  }

  @Test
  void testToken(){
    assertEquals("Bearer <TOKEN>", config.getConfig("api.refugio.token"));
  }

  @Test
  void testSender(){
    assertEquals("hackme", config.getConfig("mail.password"));
  }

}
