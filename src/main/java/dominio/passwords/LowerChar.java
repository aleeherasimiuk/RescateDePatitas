package dominio.passwords;

import dominio.exceptions.password.PasswordWithLowerCharException;

public class LowerChar extends RejexValidation{

  @Override
  protected RuntimeException error() {
    return new PasswordWithLowerCharException();
  }
  
  @Override
  protected String regex() {
    return "(?=.*[a-z]).*";
  }
  
}
