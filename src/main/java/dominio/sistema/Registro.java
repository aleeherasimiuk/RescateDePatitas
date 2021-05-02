package dominio.sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dominio.mascota.Mascota;
import dominio.personas.Persona;
import dominio.ubicacion.Coordenadas;
import dominio.usuarios.Usuario;

public class Registro {
  private List<Usuario> dueniosRegistrados = new ArrayList<Usuario>();
  private List<Mascota> mascotasRegistradas = new ArrayList<Mascota>();
  private List<Rescate> rescates = new ArrayList<Rescate>();

  public void registrarDuenio(Usuario duenio) {
    this.dueniosRegistrados.add(duenio);
  }

  public void registrarMascota(Mascota mascota) {
    this.mascotasRegistradas.add(mascota);
  }

  public void confirmarMascotaEncontrada(Persona persona, int idMascota, List<String> fotos, String descripcion, Coordenadas lugar) {
    // TODO validar que ningun campo sea null
    if (fotos.size() < 1) {
      throw new RuntimeException("La cantidad de fotos debe ser como minimo 1."); // TODO ver si necesitamos crear una excepcion custom
    }

    Mascota mascota = this.mascotasRegistradas
                          .stream()
                          .filter(m -> m.getId() == idMascota)
                          .collect(Collectors.toList())
                          .get(0);

    if (mascota == null) {
      throw new RuntimeException("Mascota no registrada");
    }

    Rescate rescate = new Rescate(persona, mascota, fotos, descripcion, lugar);

    this.rescates.add(rescate);
  }
}
