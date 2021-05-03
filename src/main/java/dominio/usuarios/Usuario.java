package dominio.usuarios;

import dominio.mascota.Mascota;
import dominio.personas.Persona;
import dominio.sistema.Registro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends UsuarioBase{
  private Persona persona;
  private int idDuenio;
  private LocalDate fechaNacimiento;
  private List<Mascota> mascotasRegistradas;
  private static int idSiguiente=0;

  public Usuario(Persona persona, LocalDate fechaNacimiento,String nombreUsuario, String contrasenia) {
    super(nombreUsuario, contrasenia);
    this.persona = persona;
    this.idDuenio = idSiguiente++;
    this.fechaNacimiento = fechaNacimiento;
    this.mascotasRegistradas=new ArrayList<>();
    
  }

  public int getIdDuenio() {
    return idDuenio;
  }

  public void registrarUnaMascota(Registro registro,Mascota mascota){
    mascotasRegistradas.add(mascota);
    registro.registrarMascota(mascota);
  }

  
}
