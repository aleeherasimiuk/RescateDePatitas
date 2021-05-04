package dominio.sistema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dominio.Rescatista;
import dominio.mascota.Mascota;
import dominio.ubicacion.Coordenadas;
import dominio.usuarios.Duenio;

public class Registro {
  private List<Duenio> dueniosRegistrados = new ArrayList<Duenio>();
  private List<Mascota> mascotasRegistradas = new ArrayList<Mascota>();
  private List<Rescate> rescates = new ArrayList<Rescate>();

  public void registrarDuenio(Duenio duenio) {
    this.dueniosRegistrados.add(duenio);
  }

  public void registrarMascota(Mascota mascota) {
    this.mascotasRegistradas.add(mascota);
  }

  public void registrarRescate(Rescatista rescatista, int idMascota, String descripcion,
      Coordenadas lugar, LocalDate fecha, String... fotos) {

    Mascota mascota = buscarMascota(idMascota);

    Rescate rescate = new Rescate(rescatista, mascota, descripcion, fecha);
    for (String foto : fotos) {
      rescate.agregarUnaFoto(foto);
    }
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
