package dominio.caracteristicas;

import java.util.List;

public class Caracteristica {
	private String titulo;
	private List<String> opciones;
	
	public Caracteristica(String titulo, List<String> opciones) { 
		this.titulo = titulo;
		this.opciones = opciones;
	}

	public void agregarOpciones(String titulo) {
		opciones.add(titulo);
	}

	public String getTitulo() {
		return this.titulo;
	}
	
	public Boolean tieneEstaOpcion(String opcion) {
		return opciones.contains(opcion);
	}
}
