package dominio.passwords;

public abstract class Validation {

  public final void validatePassword(String password){
    if(!condition(password)) throw error();
  }

  protected abstract boolean condition(String password);
  protected abstract RuntimeException error();

}
