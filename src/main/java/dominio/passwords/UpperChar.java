package dominio.passwords;

import javax.persistence.Entity;

import dominio.exceptions.password.PasswordWithUpperCharException;
@Entity
public class UpperChar extends RegexValidation{

  @Override
  protected RuntimeException error() {
    return new PasswordWithUpperCharException();
  }

  @Override
  protected String regex() {
    return "(?=.*[A-Z]).*";
  }
  
}
