package dominio.repositorio;

import dominio.preguntas.Respuesta;

public class RepositorioRespuestas extends Repositorio<Respuesta>{

  private static final RepositorioRespuestas INSTANCE = new RepositorioRespuestas();
  private RepositorioRespuestas() {}

  public static RepositorioRespuestas getInstance() {
    return INSTANCE;
  }

  @Override
  protected Class<Respuesta> getClassName() {
    return Respuesta.class;
  }

  
}
