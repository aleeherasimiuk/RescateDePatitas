package dominio.repositorio;

import dominio.exceptions.CaracteristicaRepetida;

public class RepositorioCaracteristicas extends Repositorio<String> {
	
	private static final RepositorioCaracteristicas INSTANCE = new RepositorioCaracteristicas();

	private RepositorioCaracteristicas(){}
	
	@Override
	public void registrar(String caracteristica) {
		validarCaracteristica(caracteristica);
		super.registrar(caracteristica);
	}

	public void borrarCaracteristica(String titulo) {
		super.repositorio.remove(titulo);
	}

	public boolean existeCaracteristica(String titulo) {
		return super.repositorio.contains(titulo);
	}

	private void validarCaracteristica(String caracteristica) {
		if (existeCaracteristica(caracteristica))
			throw new CaracteristicaRepetida();
	}

	public static RepositorioCaracteristicas getINSTANCE() {
		return INSTANCE;
	}

}
