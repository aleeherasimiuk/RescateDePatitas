package dominio.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

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

    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Pregunta> query = builder.createQuery(Pregunta.class);
    Root<Pregunta> root = query.from(Pregunta.class);

    query.select(root).where(
      builder.isTrue(root.get("global"))
    );

    return entityManager.createQuery(query).getResultList();
  }
}
