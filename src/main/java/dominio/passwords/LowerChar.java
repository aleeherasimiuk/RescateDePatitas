package dominio.passwords;

import javax.persistence.Entity;

import dominio.exceptions.password.PasswordWithLowerCharException;
@Entity
public class LowerChar extends RegexValidation{

  @Override
  protected RuntimeException error() {
    return new PasswordWithLowerCharException();
  }
  
  @Override
  protected String regex() {
    return "(?=.*[a-z]).*";
  }
  
}
