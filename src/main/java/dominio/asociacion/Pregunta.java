package dominio.asociacion;

public class Pregunta {
  private final String pregunta;
  private final boolean obligatorio; 
  private final String condicionAsociada; // <<< solicitud (?)
  private final Asociacion asociacion;
  
  public Pregunta(String pregunta, boolean obligatorio, String condicionAsociada, Asociacion asociacion) {
    super();
    this.pregunta = pregunta;
    this.obligatorio = obligatorio;
    this.condicionAsociada = condicionAsociada;
    this.asociacion = asociacion;
  }
 
}
