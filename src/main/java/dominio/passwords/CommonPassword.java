package dominio.passwords;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.exceptions.password.CommonPasswordException;

@Entity
public class CommonPassword extends Validation{

  @Override
  public boolean condition(String password) {
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    return ((BigInteger) entityManager.createNativeQuery("SELECT COUNT(*) FROM common_passwords p WHERE p.password = :password")
    .setParameter("password", password)
    .getResultList().get(0)).intValue() == 0;
  }

  @Override
  public RuntimeException error() {
    return new CommonPasswordException();
  }  
  
}
