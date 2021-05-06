package dominio.usuarios;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ValidadorContrasenia {
  private static final String FILENAME = "10k-most-common.txt";
  private static final String PATH = FileSystems.getDefault().getPath(FILENAME).toString();

  private static final String ERROR_IO = "No se pudo verificar la seguridad de su contraseña.";

  private static final String REGEX_UPPER_LOWER_NUMBER = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*";

  private static final String ERROR_LENGTH = "La contraseña debe contener como minimo 8 caracteres.";
  private static final String ERROR_UPPER_LOWER_NUMBER = "La contraseña debe contener como minimo: una minuscula, una mayuscula y un numero";
  private static final String ERROR_COMMON = "Contraseña vulnerable, elegir otra, por favor.";


  public static void validarPassword(String password) {
    esComun(password);
    validar(password, x -> x.length() >= 8, ERROR_LENGTH);
    validar(password, x -> x.matches(REGEX_UPPER_LOWER_NUMBER), ERROR_UPPER_LOWER_NUMBER);
  }

  private static void validar(String password, Predicate<String> func, String mensajeDeError) {
    if (!func.test(password)) throw new RuntimeException(mensajeDeError);
  }

  private static void esComun(String password) {
    try (Stream<String> stream = Files.lines(Paths.get(PATH))) {
      if (stream.anyMatch(elemento -> elemento.contentEquals(password))) {
        throw new RuntimeException(ERROR_COMMON);
      }
    } catch (IOException e) {
      throw new RuntimeException(ERROR_IO);
    }
  }
}
