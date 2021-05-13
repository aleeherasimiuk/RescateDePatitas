package dominio.passwords;

import dominio.exceptions.password.PasswordWithUpperCharException;

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
