package dominio.passwords;

import dominio.exceptions.password.PasswordWithLowerCharException;

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
