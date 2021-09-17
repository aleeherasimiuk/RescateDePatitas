package dominio.usuarios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dominio.exceptions.DuenioNoPoseeMascota;
import dominio.mascota.Mascota;
import dominio.personas.DatosPersona;
import dominio.repositorio.RepositorioMascotas;

@Entity
@Table(name = "duenios")
public class Duenio extends Usuario {

  @OneToOne
  private DatosPersona datosPersona;

  public DatosPersona getDatosPersona() {
    return datosPersona;
  }

  protected Duenio(){}

  @OneToMany
  private List<Mascota> mascotasRegistradas;

  public Duenio(String username, String password, DatosPersona datosPersona) {
    super(username, password);
    this.datosPersona = datosPersona;
    this.mascotasRegistradas = new ArrayList<>();
  }

  public void registrarUnaMascota(Mascota mascota) {
    mascotasRegistradas.add(mascota);
    RepositorioMascotas.getINSTANCE().registrar(mascota);
  }

  // TODO: Comparar por otro atributo y no por la referencia a memoria.
  public boolean esMiMascota(Mascota mascota) {
    return mascotasRegistradas.contains(mascota);
  }

  public void removerMascota(Mascota mascota) {
    if (!mascotasRegistradas.contains(mascota)) throw new DuenioNoPoseeMascota(mascota.getNombre());
    mascotasRegistradas.remove(mascota);
    RepositorioMascotas.getINSTANCE().borrar(mascota);
  }
}
