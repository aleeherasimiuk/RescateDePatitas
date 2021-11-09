package dominio.usuarios;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.mindrot.jbcrypt.BCrypt;

import dominio.repositorio.RepositorioValidaciones;
import dominio.tareas.ValidadorPassword;
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
    this.password = new ValidadorPassword().encriptarPassword(password);
  }

  public Boolean esAdmin(){
    return false;
  }
}
