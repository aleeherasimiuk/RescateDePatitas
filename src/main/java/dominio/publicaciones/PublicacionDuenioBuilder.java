package dominio.publicaciones;

import java.util.ArrayList;
import java.util.List;

import dominio.asociacion.Asociacion;
import dominio.mascota.Mascota;
import dominio.usuarios.Duenio;
import dominio.preguntas.Pregunta;
import dominio.repositorio.RepositorioPreguntas;

public class PublicacionDuenioBuilder {
  private Duenio duenio;
  private Mascota mascota;
  private Asociacion asociacion;
  private List<Respuesta> respuestas;

  public PublicacionDuenioBuilder(Duenio duenio, Mascota mascota) {
    this.duenio = duenio;
    this.mascota = mascota;
    this.respuestas = new ArrayList<>();
  }

  public PublicacionDuenioBuilder setAsociacion(Asociacion asociacion) {
    this.asociacion = asociacion;
    return this;
  }

  public PublicacionDuenioBuilder responderPregunta(Pregunta pregunta, String respuesta) {
    if (!pregunta.esRespuestaValida(respuesta))
      throw new RuntimeException("Respuesta invalida para la pregunta: " + pregunta.getPreguntaDuenio());

    respuestas.add(new Respuesta(pregunta, respuesta));
    return this;
  }

  public PublicacionDuenio build() {
    final int cantPreguntasTotal =  asociacion.cantidadDePreguntas() + RepositorioPreguntas.getInstance().cantidadRegistros();
    if (cantPreguntasTotal != respuestas.size())
      throw new RuntimeException("Hay preguntas sin responder.");
    return new PublicacionDuenio(duenio, mascota, asociacion, respuestas);
  }
}
