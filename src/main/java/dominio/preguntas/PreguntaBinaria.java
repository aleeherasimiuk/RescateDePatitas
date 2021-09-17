package dominio.preguntas;

import javax.persistence.Entity;

@Entity
public class PreguntaBinaria extends PreguntaCerrada {

  public PreguntaBinaria(String preguntaDuenio, String preguntaAdoptante) {
    super(preguntaDuenio, preguntaAdoptante,"SI", "NO");
  }
}
