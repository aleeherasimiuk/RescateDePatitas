package dominio.caracteristicas;

import java.util.ArrayList;
import java.util.List;

public class Caracteristica {
	private String titulo;
	private List<String> opciones;
	
	public Caracteristica(String titulo) { 
		this.titulo = titulo.toUpperCase();
		this.opciones = new ArrayList<String>();
	}

	public void agregarOpcion(String opcion) {
		opciones.add(opcion.toUpperCase());
	}

	public String getTitulo() {
		return this.titulo;
	}
	
	public Boolean tieneEstaOpcion(String opcion) {
		return opciones.contains(opcion.toUpperCase());
	}

	public List<String> opciones(){
		return opciones;
	}
}
