package dominio.usuarios;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends Usuario{

  protected Admin(){}

  public Admin(String username, String password) {
    super(username, password);
  }

  @Override
  public Boolean esAdmin(){
    return true;
  }
}