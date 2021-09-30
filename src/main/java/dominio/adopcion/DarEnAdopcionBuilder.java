package dominio.adopcion;

import dominio.exceptions.HayPreguntasSinResponder;
import dominio.mascota.Mascota;
import dominio.repositorio.RepositorioPreguntas;
import dominio.usuarios.Duenio;

public class DarEnAdopcionBuilder extends AdopcionBuilder {

  private Mascota mascota;

  public DarEnAdopcionBuilder(Duenio duenio, Mascota mascota) {
    super(duenio);
    this.mascota = mascota;
  }

  public DarEnAdopcion build() {
    validate();
    return new DarEnAdopcion(getDuenio(), mascota, getAsociacion(), getRespuestas());
  }

  @Override
  public void validate() {
    final int cantPreguntasTotal =  getAsociacion().cantidadDePreguntas() + RepositorioPreguntas.getInstance().globales().size();
    if (cantPreguntasTotal != getRespuestas().size())
      throw new HayPreguntasSinResponder();
  }
}
