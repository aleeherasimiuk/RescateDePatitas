package dominio.repositorio;

import java.util.List;
import java.util.stream.Collectors;

import dominio.preguntas.Pregunta;

public class RepositorioPreguntas extends Repositorio<Pregunta> {

  private static final RepositorioPreguntas INSTANCE = new RepositorioPreguntas();

  private RepositorioPreguntas() {}

  public static RepositorioPreguntas getInstance() {
    return INSTANCE;
  }

  @Override
  protected Class<Pregunta> getClassName() {
    return Pregunta.class;
  }

  //TODO: Refactor
  public List<Pregunta> globales() {
    return this.todos().stream().filter(pregunta -> pregunta.esGlobal()).collect(Collectors.toList());
  }
}
