package dominio.preguntas;

public class Pregunta {
  private String preguntaDuenio;
  private String preguntaAdoptante;
  private MatchWithField matchWithField;

  public Pregunta(String preguntaDuenio, String preguntaAdoptante, MatchWithField matchWithField) {
    this.preguntaDuenio = preguntaDuenio;
    this.preguntaAdoptante = preguntaAdoptante;
    this.matchWithField = matchWithField;
  }

  public MatchWithField getMatchWithField() {
    return matchWithField;
  }
  public boolean esRespuestaValida(String respuesta) {
    return !respuesta.isEmpty();
  }

  public String getPreguntaDuenio() {
    return preguntaDuenio;
  }
}
