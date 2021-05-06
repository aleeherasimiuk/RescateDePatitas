package dominio.usuarios;

import dominio.mascota.Mascota;
import dominio.personas.Contacto;
import dominio.personas.Persona;
import dominio.personas.TipoDeDocumento;
import dominio.sistema.RepositorioMascotas;
import dominio.util.Lista;

import java.time.LocalDate;

public class Duenio extends Persona {
  private Usuario usuario;
  private Lista<Mascota> mascotasRegistradas;

  public Duenio(String apellido, String nombre, TipoDeDocumento tipoDocumento, int numeroDocumento, Contacto contacto,
      String nombreUsuario, String contrasenia, LocalDate fechaNacimiento) {
    super(apellido, nombre, tipoDocumento, numeroDocumento, contacto, fechaNacimiento);
    this.usuario = new Usuario(nombreUsuario, contrasenia);
    this.mascotasRegistradas = new Lista<>();
  }


  public void registrarUnaMascota(Mascota mascota) {
    mascotasRegistradas.add(mascota);
    RepositorioMascotas.getINSTANCE().registrarMascota(mascota);
  }

  public String getNombreDeUsuario() {
    return usuario.getNombreUsuario();
  }

  public boolean esMiMascota(Mascota mascota){
    return mascotasRegistradas.contains(mascota);
  }


}
