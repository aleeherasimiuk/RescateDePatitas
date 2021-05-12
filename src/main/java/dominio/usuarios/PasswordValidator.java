package dominio.usuarios;

import dominio.exceptions.CommonPasswordException;
import dominio.exceptions.FileNotFound;
import dominio.exceptions.PasswordLengthException;
import dominio.exceptions.UpperLowerNumberException;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class PasswordValidator {
  private static final String FILENAME = "10k-most-common.txt";
  private static final String PATH = FileSystems.getDefault().getPath(FILENAME).toString();

  private static final String REGEX_UPPER_LOWER_NUMBER = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*";

  public static void validate(String password) {
    isCommon(password);
    validateCondition(password, x -> x.length() >= 8, new PasswordLengthException());
    validateCondition(password, x -> x.matches(REGEX_UPPER_LOWER_NUMBER), new UpperLowerNumberException());
  }

  private static void validateCondition(String password, Predicate<String> validator, RuntimeException error) {
    if (!validator.test(password)) throw error;
  }

  private static void isCommon(String password) {
    try (Stream<String> stream = Files.lines(Paths.get(PATH))) {
      if (stream.anyMatch(elemento -> elemento.contentEquals(password))) {
        throw new CommonPasswordException();
      }
    } catch (IOException e) {
      throw new FileNotFound();
    }
  }
}
