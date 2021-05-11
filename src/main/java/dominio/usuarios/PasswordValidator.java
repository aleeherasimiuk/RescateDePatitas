package dominio.usuarios;

import dominio.exceptions.ErrorCommon;
import dominio.exceptions.ErrorIO;
import dominio.exceptions.ErrorLength;
import dominio.exceptions.ErrorUpperLowerNumber;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class PasswordValidator {
  private static final String FILENAME = "10k-most-common.txt";
  private static final String PATH = FileSystems.getDefault().getPath(FILENAME).toString();

  private static final String ERROR_IO = "¡Lo sentimos! Por un error interno no se pudo verificar la seguridad de su contraseña.";

  private static final String REGEX_UPPER_LOWER_NUMBER = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*";

  private static final String ERROR_LENGTH = "La contraseña debe contener como minimo 8 caracteres.";
  private static final String ERROR_UPPER_LOWER_NUMBER = "La contraseña debe contener como minimo: una minuscula, una mayuscula y un numero";
  private static final String ERROR_COMMON = "Contraseña vulnerable, elegir otra, por favor.";


  public static void validate(String password) {
    isCommon(password);
    validateCondition(password, x -> x.length() >= 8, new ErrorLength());
    validateCondition(password, x -> x.matches(REGEX_UPPER_LOWER_NUMBER), new ErrorUpperLowerNumber());
  }

  private static void validateCondition(String password, Predicate<String> validator, RuntimeException error) {
    if (!validator.test(password)) throw error;
  }

  private static void isCommon(String password) {
    try (Stream<String> stream = Files.lines(Paths.get(PATH))) {
      if (stream.anyMatch(elemento -> elemento.contentEquals(password))) {
        throw new ErrorCommon();
      }
    } catch (IOException e) {
      throw new ErrorIO();
    }
  }
}
