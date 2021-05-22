package dominio.repositorio;

import java.util.List;

import dominio.exceptions.CaracteristicaInvalida;
import dominio.exceptions.CaracteristicaRepetida;
import dominio.mascota.Caracteristica;

public class RepositorioCaracteristicas extends Repositorio<Caracteristica> {
	
	private static final RepositorioCaracteristicas INSTANCE = new RepositorioCaracteristicas();

	private RepositorioCaracteristicas(){}
	
	@Override
	public void registrar(Caracteristica caracteristica) {
		validarCaracteristica(caracteristica);
		super.registrar(caracteristica);
	}

	public void borrarCaracteristica(String titulo) {
		super.repositorio.removeIf(caracteristica -> caracteristica.esEstaCaracteristica(titulo));
	}

	public boolean existeCaracteristica(String titulo) {
		return super.repositorio.contains(caracteristica -> caracteristica.esEstaCaracteristica(titulo));
	}

	private void validarCaracteristica(Caracteristica caracteristica) {
		if (existeCaracteristica(caracteristica.getTitulo()))
			throw new CaracteristicaRepetida();
	}

	public Caracteristica obtenerCaracteristica(String titulo) {
		return super.buscar(caracteristica -> caracteristica.esEstaCaracteristica(titulo));
	}

	public List<String> opcionesDe(String nombreCaracteristica) {
		if(existeCaracteristica(nombreCaracteristica)) throw new CaracteristicaInvalida();
		return super.buscar(caracteristica -> caracteristica.esEstaCaracteristica(nombreCaracteristica)).opciones();
	}

	public static RepositorioCaracteristicas getINSTANCE() {
		return INSTANCE;
	}


}
