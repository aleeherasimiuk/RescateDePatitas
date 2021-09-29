package dominio.adopcion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import dominio.asociacion.Asociacion;
import dominio.exceptions.RespuestaInvalida;
import dominio.preguntas.Pregunta;
import dominio.preguntas.Respuesta;
import dominio.repositorio.RepositorioRespuestas;
import dominio.usuarios.Duenio;

public abstract class AdopcionBuilder {
  private Asociacion asociacion;
  private Duenio duenio;
  private List<Respuesta> respuestas;

  public AdopcionBuilder(Duenio duenio) {
    this.duenio = duenio;
    respuestas = new ArrayList<>();
  }
  
  public AdopcionBuilder setAsociacion(Asociacion asociacion) {
    this.asociacion = asociacion;
    return this;
  }

  public AdopcionBuilder responderPregunta(Pregunta pregunta, String respuesta) {
    if (!pregunta.esRespuestaValida(respuesta))
      throw new RespuestaInvalida(pregunta.getPreguntaDuenio());

    Respuesta resp = new Respuesta(pregunta, respuesta);
    respuestas.add(resp);
    RepositorioRespuestas.getInstance().registrar(resp);
    return this;
  }

  public abstract void validate();

  public List<Respuesta> getRespuestas() {
    return respuestas;
  }

  public Asociacion getAsociacion() {
    return asociacion;
  }

  public Duenio getDuenio() {
    return duenio;
  }
}
