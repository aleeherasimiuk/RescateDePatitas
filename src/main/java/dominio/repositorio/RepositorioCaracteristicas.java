package dominio.repositorio;


import dominio.exceptions.CaracteristicaRepetida;
import dominio.mascota.Caracteristica;

public class RepositorioCaracteristicas extends Repositorio<Caracteristica> {

	private static final RepositorioCaracteristicas INSTANCE = new RepositorioCaracteristicas();

	protected RepositorioCaracteristicas(){}
	
	@Override
	public void registrar(Caracteristica caracteristica) {
		validarCaracteristica(caracteristica.getNombre());
		super.registrar(caracteristica);
	}

	public void borrarCaracteristica(Caracteristica caracteristica) {
		borrar(caracteristica);
	}

	// TODO: Refactor
	public boolean existeCaracteristica(String caracteristica) {
		return todos().stream().map(Caracteristica::getNombre).anyMatch(c -> c.equals(caracteristica));
	}

	private void validarCaracteristica(String caracteristica) {
		if (existeCaracteristica(caracteristica))
			throw new CaracteristicaRepetida();
	}

	public static RepositorioCaracteristicas getINSTANCE() {
		return INSTANCE;
	}

	@Override
  protected Class<Caracteristica> getClassName() {
    return Caracteristica.class;
  }

}
