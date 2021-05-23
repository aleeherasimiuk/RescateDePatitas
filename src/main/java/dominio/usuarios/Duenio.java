package dominio.usuarios;

import dominio.mascota.Mascota;
import dominio.personas.DatosPersona;
import dominio.repositorio.RepositorioMascotas;
import dominio.rescate.Publicacion;
import dominio.util.Lista;

public class Duenio extends Usuario {

  private DatosPersona datosPersona;

  public DatosPersona getDatosPersona() {
    return datosPersona;
  }

  private Lista<Mascota> mascotasRegistradas;

  public Duenio(String username, String password, DatosPersona datosPersona) {
    super(username, password);
    this.datosPersona = datosPersona;
    this.mascotasRegistradas = new Lista<>();
  }

  public void registrarUnaMascota(Mascota mascota) {
    mascotasRegistradas.add(mascota);
    RepositorioMascotas.getINSTANCE().registrar(mascota);
  }

  public boolean esMiMascota(Mascota mascota) {
    return mascotasRegistradas.contains(mascota);
  }
}
