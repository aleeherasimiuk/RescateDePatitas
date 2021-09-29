package dominio.tareas;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dominio.asociacion.Asociacion;
import dominio.preguntas.Pregunta;
import dominio.repositorio.RepositorioPreguntas;

public class ObtenerPreguntas {

  public List<Pregunta> preguntasDuenio(Asociacion asociacion){
    Stream<Pregunta> preguntasAsociacion = preguntasAsociacion(asociacion, Pregunta::getPreguntaDuenio);
    Stream<Pregunta> preguntasGlobales   = preguntasGlobales(Pregunta::getPreguntaDuenio);
    return Stream.concat(preguntasAsociacion, preguntasGlobales).collect(Collectors.toList());
  }

  public List<Pregunta> preguntasAdoptante(Asociacion asociacion){
    Stream<Pregunta> preguntasAsociacion = preguntasAsociacion(asociacion, Pregunta::getPreguntaAdoptante);
    Stream<Pregunta> preguntasGlobales   = preguntasGlobales(Pregunta::getPreguntaAdoptante);
    return Stream.concat(preguntasAsociacion, preguntasGlobales).collect(Collectors.toList());
  }

  private Stream<Pregunta> preguntasAsociacion(Asociacion asociacion, Function<Pregunta, String> funcion){
    return preguntasSegun(asociacion.getPreguntasAdopcion().stream(), funcion);
  }

  private Stream<Pregunta> preguntasGlobales(Function<Pregunta, String> funcion){
    return preguntasSegun(RepositorioPreguntas.getInstance().todos().stream(), funcion);
  }

  private Stream<Pregunta> preguntasSegun(Stream <Pregunta> stream, Function<Pregunta, String> funcion){
    return stream.filter(pregunta -> funcion.apply(pregunta) != null);
  }
  
}
