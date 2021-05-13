package dominio.passwords;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import dominio.exceptions.FileNotFound;
import dominio.exceptions.password.CommonPasswordException;

public class CommonPassword extends Validacion{

  private static final String FILENAME = "10k-most-common.txt";
  private static final String PATH = FileSystems.getDefault().getPath(FILENAME).toString();


  private static boolean isCommon(String password, Stream<String> stream) {
    return stream.anyMatch(elemento -> elemento.contentEquals(password));
  }

  @Override
  protected boolean condition(String password) {
    try(Stream<String> stream = Files.lines(Paths.get(PATH))) {
      return isCommon(password, stream);
    } catch (IOException e) {
      throw new FileNotFound();
    }
  }

  @Override
  protected RuntimeException error() {
    return new CommonPasswordException();
  }


  
  
}
