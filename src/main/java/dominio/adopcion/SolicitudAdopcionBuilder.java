package dominio.adopcion;

import dominio.exceptions.HayPreguntasSinResponder;
import dominio.tareas.ObtenerPreguntas;
import dominio.usuarios.Duenio;
public class SolicitudAdopcionBuilder extends AdopcionBuilder{

  public SolicitudAdopcionBuilder(Duenio duenio) {
    super(duenio);
  }

  @Override
  public void validate() {
    ObtenerPreguntas preguntas = new ObtenerPreguntas();
    final int cantPreguntasTotal = preguntas.preguntasAdoptante(getAsociacion()).size();
    if (cantPreguntasTotal != getRespuestas().size())
      throw new HayPreguntasSinResponder();
    
  }

  public SolicitudAdopcion build() {
    validate();
    return new SolicitudAdopcion(getDuenio(), getAsociacion(), getRespuestas());
  }

}
