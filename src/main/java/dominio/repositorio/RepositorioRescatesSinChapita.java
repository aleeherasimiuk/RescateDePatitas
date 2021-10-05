package dominio.repositorio;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import dominio.rescate.EstadoPublicacion;
import dominio.rescate.RescateSinChapita;

public class RepositorioRescatesSinChapita extends Repositorio<RescateSinChapita>{
  private static final RepositorioRescatesSinChapita INSTANCE = new RepositorioRescatesSinChapita();

  private RepositorioRescatesSinChapita() {}

  public List<RescateSinChapita> publicacionesAprobadas(){
    //return filtrar(RescateSinChapita::estaAprobada);

    return query(entityManager -> {

      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<RescateSinChapita> query = builder.createQuery(RescateSinChapita.class);
      Root<RescateSinChapita> root = query.from(RescateSinChapita.class);

      query.select(root).where(
        builder.equal(root.get("estado"), EstadoPublicacion.APROBADA)
      );

      return entityManager.createQuery(query).getResultList();
    });
  } 

  public List<RescateSinChapita> publicacionesPendientes(){
    return query(entityManager -> {

      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<RescateSinChapita> query = builder.createQuery(RescateSinChapita.class);
      Root<RescateSinChapita> root = query.from(RescateSinChapita.class);

      query.select(root).where(
        builder.equal(root.get("estado"), EstadoPublicacion.PENDIENTE)
      );

      return entityManager.createQuery(query).getResultList();
    });
  }

  public static RepositorioRescatesSinChapita getINSTANCE(){
    return INSTANCE;
  }
  
  @Override
  protected Class<RescateSinChapita> getClassName() {
    return RescateSinChapita.class;
  }
}
