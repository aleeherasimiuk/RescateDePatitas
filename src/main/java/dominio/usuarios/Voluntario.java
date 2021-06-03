package dominio.usuarios;

import dominio.rescate.Publicacion;

public class Voluntario extends Usuario{
  public Voluntario(String username, String password){
    super(username,password);
  }

  // TODO: WTF?
  public void aprobarPublicacion(Publicacion publicacion){
    publicacion.aprobar();
  }
}
