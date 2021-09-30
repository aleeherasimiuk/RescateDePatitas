package dominio.passwords;

import javax.persistence.Entity;

import dominio.exceptions.password.PasswordLengthException;
@Entity
public class PasswordLength extends Validation{

  @Override
  protected boolean condition(String password) {
    return password.length() >= 8;
  }

  @Override
  protected RuntimeException error() {
    return new PasswordLengthException();
  }

 
  
}
