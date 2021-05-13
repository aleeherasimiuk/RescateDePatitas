package dominio.passwords;

import dominio.exceptions.password.PasswordWithUpperCharException;

public class UpperChar extends Validacion{

  private static final String REGEX = "(?=.*[A-Z]).*";

  @Override
  protected boolean condition(String password) {
    return password.matches(REGEX);
  }

  @Override
  protected RuntimeException error() {
    return new PasswordWithUpperCharException();
  }
  
}
