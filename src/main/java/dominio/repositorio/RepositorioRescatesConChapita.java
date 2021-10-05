package dominio.repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import dominio.mascota.Mascota;
import dominio.rescate.RescateConChapita;

public class RepositorioRescatesConChapita extends Repositorio<RescateConChapita>{
  private static final RepositorioRescatesConChapita INSTANCE = new RepositorioRescatesConChapita();

  private RepositorioRescatesConChapita() {}

  public List<Mascota> mascotasEncontradasEnLosUltimos10Dias() {

    return query(entityManager -> {

      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<RescateConChapita> query = builder.createQuery(RescateConChapita.class);
      Root<RescateConChapita> root = query.from(RescateConChapita.class);
    
      query.select(root).where(
        builder.and(
          builder.greaterThan(root.join("datosRescate").get("fecha"), LocalDate.now().minusDays(10))
        )
      );

      return entityManager.createQuery(query).getResultList().stream()
        .map(RescateConChapita::getMascota)
        .collect(Collectors.toList());
    });
  }

  public static RepositorioRescatesConChapita getINSTANCE(){
    return INSTANCE;
  }

  @Override
  protected Class<RescateConChapita> getClassName() {
    return RescateConChapita.class;
  }
}
