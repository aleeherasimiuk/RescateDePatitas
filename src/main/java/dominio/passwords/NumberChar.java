package dominio.passwords;

import dominio.exceptions.password.PasswordWithNumberCharException;

public class NumberChar extends Validacion{

  private static final String REGEX = "(?=.*\\d).*";

  @Override
  protected boolean condition(String password) {
    return password.matches(REGEX);
  }

  @Override
  protected RuntimeException error() {
    return new PasswordWithNumberCharException();
  }
  
}
