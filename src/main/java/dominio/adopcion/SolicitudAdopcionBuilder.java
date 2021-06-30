package dominio.adopcion;

import dominio.asociacion.Asociacion;
import dominio.preguntas.Pregunta;
import dominio.publicaciones.PublicacionDuenio;
import dominio.publicaciones.PublicacionDuenioBuilder;
import dominio.publicaciones.Respuesta;
import dominio.repositorio.RepositorioPreguntas;
import dominio.usuarios.Duenio;
import dominio.util.Lista;

import java.util.List;

public class SolicitudAdopcionBuilder {
  private List<Respuesta> respuestas;
  private Asociacion asociacion;
  private Duenio adoptante;

  public SolicitudAdopcionBuilder(Duenio adoptante) {
    this.adoptante = adoptante;
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
    final int cantPreguntasTotal =  asociacion.cantidadDePreguntas() + RepositorioPreguntas.getInstance().cantidadRegistros();
    if (cantPreguntasTotal != respuestas.size())
      throw new RuntimeException("Hay preguntas sin responder.");
    return new SolicitudAdopcion(adoptante,asociacion,respuestas);
  }

}
