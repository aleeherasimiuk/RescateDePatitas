package dominio.caracteristicas;

import java.util.ArrayList;
import java.util.List;

public class Caracteristicas {
	private List<Caracteristica> caracteristicas;
	
	public Caracteristicas() {
		this.caracteristicas = new ArrayList<>();
	}
	
	void agregarCaracteristica(Caracteristica caracteristica) {
		caracteristicas.add(caracteristica);
	}

	void borrarCaracteristica(String titulo) {
		this.caracteristicas.removeIf(caracteristica -> caracteristica.getTitulo().equals(titulo));
	}
}
