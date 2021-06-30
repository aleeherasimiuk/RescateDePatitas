package dominio.repositorio;

import dominio.preguntas.Pregunta;

public class RepositorioPreguntas extends Repositorio<Pregunta> {

  private static final RepositorioPreguntas INSTANCE = new RepositorioPreguntas();

  private RepositorioPreguntas() {}

  public static RepositorioPreguntas getInstance() {
    return INSTANCE;
  }
}
