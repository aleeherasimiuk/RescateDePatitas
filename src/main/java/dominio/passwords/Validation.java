package dominio.passwords;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import persistencia.PersistentEntity;

@Entity
@Table(name = "password_validations")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Validation extends PersistentEntity{

  private boolean activada = true;

  protected Validation() {}

  public final void validatePassword(String password){
    if(!condition(password)) throw error();
  }

  protected abstract boolean condition(String password);
  protected abstract RuntimeException error();

  public boolean activada() {
    return activada;
  }

  public void activar(){
    activada = true;
  }

  public void desactivar(){
    activada = false;
  }
}
