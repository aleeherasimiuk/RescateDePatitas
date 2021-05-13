package dominio.passwords;

public abstract class RejexValidation extends Validation{

  protected abstract String regex();

  @Override
  protected boolean condition(String password) {
    return password.matches(regex());
  }
  
}
