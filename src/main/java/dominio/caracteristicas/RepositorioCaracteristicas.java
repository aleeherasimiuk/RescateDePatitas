package dominio.caracteristicas;

import java.util.List;

import dominio.util.Lista;

public class RepositorioCaracteristicas {
	private Lista<Caracteristica> caracteristicas;

	public RepositorioCaracteristicas() {
		this.caracteristicas = new Lista<>();
	}

	public void agregarCaracteristica(Caracteristica caracteristica) {
		validarCaracteristica(caracteristica);
		caracteristicas.add(caracteristica);
	}

	public void borrarCaracteristica(String titulo) {
		this.caracteristicas.removeIf(caracteristica -> caracteristica.getTitulo().equals(titulo.toUpperCase()));
	}

	public boolean existeCaracteristica(String titulo) {
		return caracteristicas.contains(caracteristica -> caracteristica.getTitulo().equals(titulo.toUpperCase()));
	}

	private void validarCaracteristica(Caracteristica caracteristica) {
		if (existeCaracteristica(caracteristica.getTitulo()))
			throw new RuntimeException(
					"Ya existe una caracteristica con ese titulo. Verifique si se trata de un error o intente con otro titulo");
	}

	public Caracteristica obtenerCaracteristica(String titulo) {
		return caracteristicas.find(caracteristica -> caracteristica.getTitulo().equals(titulo.toUpperCase()));
	}

	public List<String> opcionesDe(String nombreCaracteristica) {
		return caracteristicas.find(caracteristica -> caracteristica.getTitulo().equals(nombreCaracteristica)).opciones();
	}

}
