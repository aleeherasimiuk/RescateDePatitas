package dominio.adopcion;

import dominio.asociacion.Asociacion;
import dominio.preguntas.Pregunta;
import dominio.preguntas.Respuesta;
import dominio.tareas.ObtenerPreguntas;
import dominio.usuarios.Duenio;

import java.util.ArrayList;
import java.util.List;

public class SolicitudAdopcionBuilder {
  private List<Respuesta> respuestas;
  private Asociacion asociacion;
  private Duenio adoptante;

  public SolicitudAdopcionBuilder(Duenio adoptante) {
    this.adoptante = adoptante;
    this.respuestas = new ArrayList<>();
  }

  public SolicitudAdopcionBuilder setAsociacion(Asociacion asociacion) {
    this.asociacion = asociacion;
    return this;
  }

  public SolicitudAdopcionBuilder responderPregunta(Pregunta pregunta, String respuesta) {
    if (!pregunta.esRespuestaValida(respuesta))
      throw new RuntimeException("Respuesta invalida para la pregunta: " + pregunta.getPreguntaDuenio());

    respuestas.add(new Respuesta(pregunta, respuesta));
    return this;
  }

  public SolicitudAdopcion build() {
    ObtenerPreguntas preguntas = new ObtenerPreguntas();
    final int cantPreguntasTotal = preguntas.preguntasAdoptante(asociacion).size();
    if (cantPreguntasTotal != respuestas.size())
      throw new RuntimeException("Hay preguntas sin responder.");
    return new SolicitudAdopcion(adoptante,asociacion,respuestas);
  }

}
