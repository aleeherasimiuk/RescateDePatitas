package dominio.passwords;

import dominio.exceptions.password.PasswordWithNumberCharException;

public class NumberChar extends RejexValidation{

  @Override
  protected RuntimeException error() {
    return new PasswordWithNumberCharException();
  }

  @Override
  protected String regex() {
    return "(?=.*\\d).*";
  }
  
}
