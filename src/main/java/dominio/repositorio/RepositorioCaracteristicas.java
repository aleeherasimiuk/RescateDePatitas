package dominio.repositorio;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

	public void borrarPorNombre(String caracteristica) {
		transaction(entityManager -> {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaDelete<Caracteristica> query = builder.createCriteriaDelete(Caracteristica.class);
			Root<Caracteristica> root = query.from(Caracteristica.class);
			
			query.where(builder.equal(root.get("nombre"), caracteristica));
			entityManager.createQuery(query).executeUpdate();
		});
	}

	public boolean existeCaracteristica(String caracteristica) {
		return query(entityManager -> {

			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> query = builder.createQuery(Long.class);
			Root<Caracteristica> root = query.from(Caracteristica.class);
			query.select(builder.count(root)).where(
				builder.equal(root.get("nombre"), caracteristica)
			);
			return entityManager.createQuery(query).getSingleResult() > 0;

		});
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
