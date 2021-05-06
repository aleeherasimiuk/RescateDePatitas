package dominio.repositorio;

import dominio.mascota.Mascota;
import dominio.usuarios.Administrador;
import dominio.usuarios.Duenio;
import dominio.util.Lista;

public class RepositorioUsuarios {
  private Lista<Administrador> repoAdministrador;
  private Lista<Duenio> repoDuenio;
  private static final RepositorioUsuarios INSTANCE = new RepositorioUsuarios();

  private RepositorioUsuarios() {
    this.repoDuenio = new Lista<>();
    this.repoAdministrador = new Lista<>();
  }

  public void registrarDuenio(Duenio duenio){
    repoDuenio.add(duenio);
  }

  public Duenio duenioDe(Mascota mascota){
    return repoDuenio.find(duenio -> duenio.esMiMascota(mascota));
  }

  public void eliminarDuenio(Duenio duenio){
    repoDuenio.remove(duenio);
  }

  public void registrarAdministrador(Administrador administrador){
    repoAdministrador.add(administrador);
  }

  public void eliminarAdministrador(Administrador administrador){
    repoAdministrador.remove(administrador);
  }


}
