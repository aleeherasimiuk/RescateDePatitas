package dominio.passwords;

import javax.persistence.Entity;

import dominio.exceptions.password.PasswordWithNumberCharException;
@Entity
public class NumberChar extends RegexValidation{

  @Override
  protected RuntimeException error() {
    return new PasswordWithNumberCharException();
  }

  @Override
  protected String regex() {
    return "(?=.*\\d).*";
  }
  
}
