package dominio.passwords;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.exceptions.password.CommonPasswordException;

public class CommonPassword extends Validation{

  @Override
  public boolean condition(String password) {
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    return entityManager.createNativeQuery("SELECT * FROM common_passwords p WHERE p.password = :password")
      .setParameter("password", password)
      .getResultList().size() > 0;
  }

  @Override
  public RuntimeException error() {
    return new CommonPasswordException();
  }  
  
}
