package dominio.usuarios;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ValidadorContrasenia {
  private String fileName;

  public ValidadorContrasenia() {
    this.fileName = FileSystems.getDefault().getPath("10k-most-common.txt").toString();
  }

  public void validadorContrasenia(String contrasenia) {
    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
      if (stream.anyMatch(elemento -> elemento.contentEquals(contrasenia))) {
        throw new RuntimeException("Contraseña vulnerable, elegir otra, por favor.");
      }
    } catch (IOException e) {
      throw new RuntimeException("No se pudo verificar la seguridad de su contraseña.");
    }
  }
}
