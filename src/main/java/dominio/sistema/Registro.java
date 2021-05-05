package dominio.sistema;

import java.time.LocalDate;
import java.util.List;

import dominio.Rescatista;
import dominio.mascota.Mascota;
import dominio.ubicacion.Coordenadas;
import dominio.usuarios.Duenio;
import dominio.util.Lista;

public class Registro {
  private Lista<Duenio> dueniosRegistrados = new Lista<Duenio>();
  private Lista<Mascota> mascotasRegistradas = new Lista<Mascota>();
  private Lista<Rescate> rescates = new Lista<Rescate>();

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
    return rescates.filter(Rescate::sucedioDentroDeLosUltimos10Dias).map(Rescate::getMascota);
  }

  public int cantDeMascotasRegistradas() {
    return mascotasRegistradas.size();
  }

  public Mascota buscarMascota(int idMascota) {
   return mascotasRegistradas.find(m -> m.getId() == idMascota);
  }
}
