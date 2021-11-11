package dominio.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.mascota.Mascota;
import dominio.usuarios.Duenio;

public class RepositorioDuenios extends Repositorio<Duenio>{
  private static final RepositorioDuenios INSTANCE = new RepositorioDuenios();

  private RepositorioDuenios() {}

  public Duenio duenioDe(Mascota mascota){

    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Duenio> query = builder.createQuery(Duenio.class);
    Root<Duenio> root = query.from(Duenio.class);

    query.select(root).where(
      builder.and(
        builder.equal(root.join("mascotasRegistradas").get("id"), mascota.getId())
      )
    );
    TypedQuery<Duenio> q = entityManager.createQuery(query);
    return q.getSingleResult();
  }

  public static RepositorioDuenios getInstance() {
    return INSTANCE;
  }

  @Override
  protected Class<Duenio> getClassName() {
    return Duenio.class;
  }

  public Duenio getDuenioByUsername(String user) {
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Duenio> query = builder.createQuery(Duenio.class);
    Root<Duenio> root = query.from(Duenio.class);

    query.select(root).where(
        builder.equal(root.get("username"),user)
    );
    TypedQuery<Duenio> q = entityManager.createQuery(query);
    Duenio result = null;

    try{
      result = q.getSingleResult();
    }catch (Exception e){
      return null;
    }

    return result;
  }
}
