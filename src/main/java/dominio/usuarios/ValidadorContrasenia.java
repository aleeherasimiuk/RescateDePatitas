package dominio.usuarios;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ValidadorContrasenia {
	private String fileName;
	
	public ValidadorContrasenia() {
		this.fileName = FileSystems.getDefault().getPath("10k-most-common.txt").toString();
	}
  
  public boolean esVulnerableLaContrasenia(String contrasenia) {
    List<String> list = new ArrayList<>();

    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
    	return stream.anyMatch(elemento -> elemento.contentEquals(contrasenia));
        		
    } catch (IOException e) {
        e.printStackTrace();
    }  	
    
    return false;
  }
}
