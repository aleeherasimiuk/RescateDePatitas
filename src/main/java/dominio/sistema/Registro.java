package dominio.sistema;

import java.util.ArrayList;
import java.util.List;

import dominio.mascota.Mascota;
import dominio.usuarios.Usuario;

public class Registro {
  private List<Usuario> dueniosRegistrados = new ArrayList<Usuario>();
  private List<Mascota> mascotasRegistradas = new ArrayList<Mascota>();
  // private List<EncuentroDeMascota> = new ArrayList<EncuentroDeMascota>();

  public void registrarDuenio(Usuario duenio) {
    this.dueniosRegistrados.add(duenio);
  }

  public void registrarMascota(Mascota mascota) {
    this.mascotasRegistradas.add(mascota);
  }
}
