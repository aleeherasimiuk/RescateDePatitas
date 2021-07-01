package dominio.adopcion;

import java.util.ArrayList;
import java.util.List;

import dominio.asociacion.Asociacion;
import dominio.exceptions.HayPreguntasSinResponder;
import dominio.exceptions.RespuestaInvalida;
import dominio.mascota.Mascota;
import dominio.usuarios.Duenio;
import dominio.preguntas.Pregunta;
import dominio.preguntas.Respuesta;
import dominio.repositorio.RepositorioPreguntas;

public class DarEnAdopcionBuilder {
  private Duenio duenio;
  private Mascota mascota;
  private Asociacion asociacion;
  private List<Respuesta> respuestas;

  public DarEnAdopcionBuilder(Duenio duenio, Mascota mascota) {
    this.duenio = duenio;
    this.mascota = mascota;
    this.respuestas = new ArrayList<>();
  }

  public DarEnAdopcionBuilder setAsociacion(Asociacion asociacion) {
    this.asociacion = asociacion;
    return this;
  }

  public DarEnAdopcionBuilder responderPregunta(Pregunta pregunta, String respuesta) {
    if (!pregunta.esRespuestaValida(respuesta)) {
      throw new RespuestaInvalida(pregunta.getPreguntaDuenio());
    }
    respuestas.add(new Respuesta(pregunta, respuesta));
    return this;
  }

  public DarEnAdopcion build() {
    final int cantPreguntasTotal =  asociacion.cantidadDePreguntas() + RepositorioPreguntas.getInstance().cantidadRegistros();
    if (cantPreguntasTotal != respuestas.size())
      throw new HayPreguntasSinResponder();
    return new DarEnAdopcion(duenio, mascota, asociacion, respuestas);
  }
}
