package dominio.caracteristicas;

public class CaracteristicaNoDisponible extends RuntimeException{

	public CaracteristicaNoDisponible() {
		super("La característica que se intenta buscar no existe");
	}
}
