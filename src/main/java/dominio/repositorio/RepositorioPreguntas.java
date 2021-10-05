package dominio.repositorio;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

  public List<Pregunta> globales() {

    return query(entityManager -> {

      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Pregunta> query = builder.createQuery(Pregunta.class);
      Root<Pregunta> root = query.from(Pregunta.class);

      query.select(root).where(
        builder.isTrue(root.get("global"))
      );

      return entityManager.createQuery(query).getResultList();
    });
  
  
  }

  // @Override
  // public void vaciar(){
  //   paraTodos(list -> {
  //     list.forEach(pregunta -> pregunta.vaciar());
  //     return null;
  //   });
    
  //   transaction(entityManager -> {
  //     entityManager.createQuery("TRUNCATE " + getClassName().getSimpleName() + " CASCADE")
  //       .executeUpdate();
  //   });
  // }
}
