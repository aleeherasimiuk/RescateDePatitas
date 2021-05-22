package dominio.passwords;

public abstract class RegexValidation extends Validation{

  protected abstract String regex();

  @Override
  protected boolean condition(String password) {
    return password.matches(regex());
  }
  
}
