package dominio.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.usuarios.Duenio;
import dominio.usuarios.Usuario;

public class RepositorioUsuarios extends Repositorio<Usuario>{

  private static final RepositorioUsuarios INSTANCE = new RepositorioUsuarios();

  public static RepositorioUsuarios getInstance() {
    return INSTANCE;
  }

  public Usuario buscarPorNombreDeUsuario(String username){
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
    Root<Usuario> root = query.from(Usuario.class);

    query.select(root).where(
        builder.equal(root.get("username"), username)
    );
    TypedQuery<Usuario> q = entityManager.createQuery(query);
    Usuario result = null;

    try{
      result = q.getSingleResult();
    }catch (Exception e){
      return null;
    }

    return result;
  }

  public Usuario buscarPorId(Long id){
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    return entityManager.find(Usuario.class, id);
  }

  @Override
  protected Class<Usuario> getClassName() {
    return Usuario.class;
  }
  
}
