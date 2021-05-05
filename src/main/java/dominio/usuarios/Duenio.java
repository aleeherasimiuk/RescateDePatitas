package dominio.usuarios;

import dominio.mascota.Mascota;
import dominio.personas.Contacto;
import dominio.personas.Persona;
import dominio.personas.TipoDeDocumento;
import dominio.sistema.Registro;
import dominio.util.Lista;

import java.time.LocalDate;

public class Duenio extends Persona {
  private Usuario usuario;
  private int idDuenio;
  private Lista<Mascota> mascotasRegistradas;
  private static int idSiguiente = 0;

  public Duenio(String apellido, String nombre, TipoDeDocumento tipoDocumento, int numeroDocumento, Contacto contacto,
      String nombreUsuario, String contrasenia, LocalDate fechaNacimiento) {
    super(apellido, nombre, tipoDocumento, numeroDocumento, contacto, fechaNacimiento);
    this.usuario = new Usuario(nombreUsuario, contrasenia);
    this.idDuenio = idSiguiente++;
    this.mascotasRegistradas = new Lista<>();

  }

  public int getIdDuenio() {
    return idDuenio;
  }

  public void registrarUnaMascota(Registro registro, Mascota mascota) {
    mascotasRegistradas.add(mascota);
    registro.registrarMascota(mascota);
  }

  public String getNombreDeUsuario(){
    return usuario.getNombreUsuario();
  }

}
