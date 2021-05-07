package dominio.usuarios;

import dominio.mascota.Mascota;
import dominio.personas.Contacto;
import dominio.personas.Persona;
import dominio.personas.TipoDeDocumento;
import dominio.repositorio.RepositorioMascotas;
import dominio.util.Lista;

import java.time.LocalDate;

public class Duenio extends Persona {
  private Usuario usuario;
  private Lista<Mascota> mascotasRegistradas;

  public Duenio(String apellido, String nombre, TipoDeDocumento tipoDocumento, int numeroDocumento, Contacto contacto,
      String username, String password, LocalDate fechaNacimiento) {
    super(apellido, nombre, tipoDocumento, numeroDocumento, contacto, fechaNacimiento);
    this.usuario = new Usuario(username, password);
    this.mascotasRegistradas = new Lista<>();
  }


  public void registrarUnaMascota(Mascota mascota) {
    mascotasRegistradas.add(mascota);
    RepositorioMascotas.getINSTANCE().registrar(mascota);
  }

  public String getNombreDeUsuario() {
    return usuario.getUsername();
  }

  public boolean esMiMascota(Mascota mascota){
    return mascotasRegistradas.contains(mascota);
  }


}
