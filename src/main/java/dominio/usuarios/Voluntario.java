package dominio.usuarios;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "voluntarios")
public class Voluntario extends Usuario{
  protected Voluntario(){}
  public Voluntario(String username, String password){
    super(username,password);
  }
}
