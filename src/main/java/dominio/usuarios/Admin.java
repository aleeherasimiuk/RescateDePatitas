package dominio.usuarios;

import javax.persistence.Entity;
import javax.persistence.Table;

import dominio.mascota.Caracteristica;
import dominio.repositorio.RepositorioCaracteristicas;

@Entity
@Table(name = "admin")
public class Admin extends Usuario{

  protected Admin(){}

  public Admin(String username, String password) {
    super(username, password);
  }

  public void agregarUnaCaracteristica(String caracteristica) {
    RepositorioCaracteristicas.getINSTANCE().registrar(new Caracteristica(caracteristica.toUpperCase()));
  }

  public void eliminarUnaCaracteristica(String titulo) {
    RepositorioCaracteristicas.getINSTANCE().borrarCaracteristica(new Caracteristica(titulo.toUpperCase()));
  }

  public void agregarCaracteristicas(String... caracteristicas){
    for (String caracteristica : caracteristicas) {
      this.agregarUnaCaracteristica(caracteristica);
    }
  }
}