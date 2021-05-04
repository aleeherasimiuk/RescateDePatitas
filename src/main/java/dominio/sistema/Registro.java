package dominio.sistema;

import java.time.LocalDate;
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

  public void registrarRescate(Persona persona, int idMascota, List<String> fotos, String descripcion,
      Coordenadas lugar, LocalDate fecha) {

    Mascota mascota = buscarMascota(idMascota);

    Rescate rescate = new Rescate(persona, mascota,descripcion,fecha);
    rescate.setFotos(fotos);
    rescate.setLugar(lugar);
    
    this.rescates.add(rescate);
  }

  public List<Mascota> mascotasEncontradasEnLosUltimos10Dias() {
    return this.rescates.stream().filter(rescate -> rescate.sucedioDentroDeLosUltimos10Dias())
        .map(rescate -> rescate.getMascota()).collect(Collectors.toList());
  }

  public int cantDeMascotasRegistradas() {
    return mascotasRegistradas.size();
  }

  public Mascota buscarMascota(int idMascota) {
   return this.mascotasRegistradas.stream().filter(m -> m.getId() == idMascota).collect(Collectors.toList()).get(0);
  }
}
