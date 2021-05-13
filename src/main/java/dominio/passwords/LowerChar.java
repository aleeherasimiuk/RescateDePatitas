package dominio.passwords;

import dominio.exceptions.password.PasswordWithLowerCharException;

public class LowerChar extends Validacion{

  private static final String REGEX = "(?=.*[a-z]).*";

  @Override
  protected boolean condition(String password) {
    return password.matches(REGEX);
  }

  @Override
  protected RuntimeException error() {
    return new PasswordWithLowerCharException();
  }
  
}
