package dominio.caracteristicas;

import java.util.ArrayList;
import java.util.List;

public class Caracteristicas {
	private List<Caracteristica> caracteristicas;
	
	public Caracteristicas() {
		this.caracteristicas = new ArrayList<>();
	}
	
	public void agregarCaracteristica(Caracteristica caracteristica) {
		validarCaracteristica(caracteristica);
		caracteristicas.add(caracteristica);
	}

	public void borrarCaracteristica(String titulo) {
		this.caracteristicas.removeIf(caracteristica -> caracteristica.getTitulo().equals(titulo.toUpperCase()));
	}

	public boolean existeCaracteristica(String titulo){
		return caracteristicas.stream().anyMatch(caracteristica-> caracteristica.getTitulo().equals(titulo.toUpperCase()));
	}

	private void validarCaracteristica(Caracteristica caracteristica){
		if (existeCaracteristica(caracteristica.getTitulo()))
			throw new RuntimeException("Ya existe una caracteristica con ese titulo. Verifique si se trata de un error o intente con otro titulo");
	}

	public Caracteristica obtenerCaracteristica(String titulo){
		return caracteristicas.stream().filter((caracteristica -> caracteristica.getTitulo().equals(titulo.toUpperCase()))).findAny().orElseThrow(CaracteristicaNoDisponible::new);
	}

}

