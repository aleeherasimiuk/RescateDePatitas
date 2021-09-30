import static org.junit.jupiter.api.Assertions.*;

import dominio.mascota.ClaseMascota;
import dominio.mascota.Mascota;
import dominio.mascota.Sexo;
import dominio.mascota.Tamanio;
import dominio.repositorio.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;
import java.sql.Connection;

public class PersistenciaInit extends AbstractPersistenceTest implements WithGlobalEntityManager {

  @Test
  public void contextUp() {
    assertNotNull(entityManager());
  }
  @Test
  public void contextUpWithTransaction() throws Exception {
    withTransaction(() -> {});
  }

  @Test
  public void persistenciaDeModificaciones(){
    Mascota mascota = new Mascota(ClaseMascota.PERRO,"Pancho", "Pancho", 0, Sexo.MACHO, Tamanio.CHICO);
    RepositorioMascotas.getINSTANCE().registrar(mascota);
    mascota.setEdad(1);
    entityManager().clear();
    RepositorioMascotas.getINSTANCE().actualizar(mascota);
    assertEquals(1,RepositorioMascotas.getINSTANCE().todos().get(0).getEdad());
  }

  @AfterEach
  public void setup(){
    RepositorioAdministradores.getInstance().vaciar();
    RepositorioAdopcion.getInstance().vaciar();
    RepositorioAsociaciones.getInstance().vaciar();
    RepositorioCaracteristicas.getINSTANCE().vaciar();
    RepositorioDuenios.getInstance().vaciar();
    RepositorioMascotas.getINSTANCE().vaciar();
    RepositorioPreguntas.getInstance().vaciar();
    RepositorioRescatesConChapita.getINSTANCE().vaciar();
    RepositorioRescatesSinChapita.getINSTANCE().vaciar();
    RepositorioRescatistas.getInstance().vaciar();
    RepositorioRespuestas.getInstance().vaciar();
    RepositorioSolicitudesAdopcion.getInstance().vaciar();
    RepositorioValidaciones.getInstance().vaciar();


  }

}
