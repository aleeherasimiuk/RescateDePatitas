package dominio.repositorio;


import dominio.mascota.Mascota;
import dominio.usuarios.Duenio;

public class RepositorioDuenios extends Repositorio<Duenio>{
  private static final RepositorioDuenios INSTANCE = new RepositorioDuenios();

  private RepositorioDuenios() {}

  public Duenio duenioDe(Mascota mascota){
    //return paraTodos(list -> list.stream().filter(duenio -> duenio.esMiMascota(mascota)).findFirst().orElse(null));
    //return todos().stream().filter(duenio -> duenio.esMiMascota(mascota)).findFirst().orElse(null);
    // return query(entityManager -> {

    //   //return (Duenio) entityManager.createNativeQuery("SELECT * FROM " + getClassName().getSimpleName() + " e INNER JOIN " + RepositorioMascotas.getINSTANCE().getClassName().getSimpleName() + " e2 ON e.id = e2.duenio_id WHERE e2.id = " + mascota.getId()).getSingleResult();
          
    //   // return entityManager
    //   //   .createQuery(
    //   //     "SELECT e FROM " + getClassName().getSimpleName() 
    //   //     + " e, " 
    //   //     + RepositorioMascotas.getINSTANCE().getClassName().getSimpleName() 
    //   //     + " m WHERE m.id = " + mascota.getId() + " and e.id = m.duenio_id", getClassName()).getSingleResult();
    
    // //   // List<Duenio> list = entityManager
    // //   // .createQuery("SELECT e FROM " + getClassName().getSimpleName() + " e, " + RepositorioMascotas.getINSTANCE().getClassName().getSimpleName() + " m WHERE m.id = " + mascota.getId(), getClassName()).getResultList();
    // //   // list.forEach(duenio -> System.out.println(duenio.getDatosPersona().getNombre()));
    // //   // return list.get(0);

      
    // });


    return paraTodos(list -> {
      list.forEach(duenio -> System.out.println(duenio.getDatosPersona().getNombre()));
      return list.stream().filter(duenio -> duenio.esMiMascota(mascota)).findFirst().orElse(null);
    });

  }

  public static RepositorioDuenios getInstance() {
    return INSTANCE;
  }

  @Override
  protected Class<Duenio> getClassName() {
    return Duenio.class;
  }
}
