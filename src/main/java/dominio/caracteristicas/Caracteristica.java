package dominio.caracteristicas;

import dominio.util.Lista;

public class Caracteristica {
	private String titulo;
	private Lista<String> opciones;

	public Caracteristica(String titulo) {
		this.titulo = titulo.toUpperCase();
		this.opciones = new Lista<String>();
	}

	public void agregarOpcion(String opcion) {
		opciones.add(opcion.toUpperCase());
	}

	public String getTitulo() {
		return this.titulo;
	}

	public boolean tieneEstaOpcion(String opcion) {
		return opciones.contains(opcion.toUpperCase());
	}

	public Lista<String> opciones() {
		return opciones;
	}
}
