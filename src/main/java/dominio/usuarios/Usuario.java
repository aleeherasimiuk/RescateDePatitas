package dominio.usuarios;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.mindrot.jbcrypt.BCrypt;

import dominio.repositorio.RepositorioValidaciones;
import persistencia.PersistentEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@Table(name = "usuarios")
public abstract class Usuario extends PersistentEntity {

  private String username;

  protected Usuario(){}

  public String getUsername() {
    return username;
  }

  private String password;

  public String getPassword() {
    return password;
  }

  public Usuario(String username, String password) {
    this.username = username;
    validarPassword(password);
    this.password = encriptarPassword(password);
  }

  private void validarPassword(String password) {
    RepositorioValidaciones.getInstance().validatePassword(password);
  }

  private String encriptarPassword(String password) {
    return BCrypt.hashpw(password,BCrypt.gensalt(12));
  }

  public Boolean login(String password) {
    return BCrypt.checkpw(password,this.password);
  }
}
