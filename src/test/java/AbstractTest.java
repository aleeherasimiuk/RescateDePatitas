import java.util.logging.Logger;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public abstract class AbstractTest {

  @BeforeAll
  static void doNotPrintLogs() {
    Logger logger = Logger.getLogger("org.hibernate");
    logger.setUseParentHandlers(false);
  }

  @BeforeEach
  abstract void setup();

  @AfterEach
  public void tearDown() {
    PerThreadEntityManagers.getEntityManager().getTransaction().rollback();
  }
  
}
