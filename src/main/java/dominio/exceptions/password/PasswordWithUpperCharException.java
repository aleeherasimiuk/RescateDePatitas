package dominio.exceptions.password;

public class PasswordWithUpperCharException extends RuntimeException{
  public PasswordWithUpperCharException(){
    super("La contraseña debe contener como minimo una letra mayúscula");
  }
}
