package dominio.repositorio;

import dominio.rescate.Rescatista;

public class RepositorioRescatistas extends Repositorio<Rescatista>{

  private static final RepositorioRescatistas INSTANCE = new RepositorioRescatistas();
  private RepositorioRescatistas(){};


  public static RepositorioRescatistas getInstance() {
    return INSTANCE;
  }

  @Override
  protected Class<Rescatista> getClassName() {
    return Rescatista.class;
  }


  
}
