package dominio.preguntas;

import javax.persistence.Entity;

@Entity
public class PreguntaBinaria extends PreguntaCerrada {

  protected PreguntaBinaria(){}

  public PreguntaBinaria(String preguntaDuenio, String preguntaAdoptante) {
    super(false, preguntaDuenio, preguntaAdoptante,"SI", "NO");
  }

  public PreguntaBinaria(String preguntaDuenio, String preguntaAdoptante, boolean global) {
    super(global, preguntaDuenio, preguntaAdoptante,"SI", "NO");
  }
}
