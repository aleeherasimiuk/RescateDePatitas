package dominio.usuarios;

import dominio.personas.Persona;

import java.time.LocalDate;
import java.util.List;

public class Usuario extends UsuarioBase{
  private Persona persona;
  private int idDuenio;
  private LocalDate fechaNacimiento;
  private List<Mascota> mascotasRegistradas;

  public int getIdDuenio() {
    return idDuenio;
  }

  void registrarUnaMascota(Mascota mascota){
    mascotasRegistradas.add(mascota);
  }º

  
}
