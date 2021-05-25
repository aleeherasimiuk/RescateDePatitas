package dominio.rescate;

import dominio.asociacion.Asociacion;
import dominio.mascota.Mascota;
import dominio.repositorio.RepositorioAsociaciones;
import dominio.repositorio.RepositorioPublicaciones;
import dominio.util.Lista;
public class Publicacion {
  
  private final Rescate rescate;
  private EstadoPublicacion estado;
  private Asociacion asociacionAsignada;

  public Publicacion(Rescate rescate) {
    this.rescate = rescate;
    this.estado = EstadoPublicacion.PENDIENTE;
  }

  public void aprobar(){
    estado = EstadoPublicacion.APROBADA;
  }

  public void rechazar(){
    estado = EstadoPublicacion.RECHAZADA;
  }

  public Lista<String> fotos(){
    return rescate.getFotos();
  }

  public String datosNoSensibles(){
    return ""; //TODO
  }

  public boolean estaAprobada(){
    return estado == EstadoPublicacion.APROBADA;
  }

  public boolean estaPendiente(){
    return estado == EstadoPublicacion.PENDIENTE;
  }

  public void registrarse(){
    RepositorioPublicaciones.getINSTANCE().registrar(this);
  }

  public void identificarMascota(Mascota mascota){
    rescate.setMascota(mascota);
    // TODO: Avisar al rescatista
  }

  public void asignarAsociacion(){
    if(asociacionAsignada != null) throw new RuntimeException("La publicación ya tiene una asociación asignada");
    asociacionAsignada = RepositorioAsociaciones.getInstance().obtenerLaMasCercana(rescate);
  }

  public Asociacion getAsociacionAsignada() {
    if(asociacionAsignada == null) throw new RuntimeException("No se ha asignado ninguna asociación");
    return asociacionAsignada;
  }

}
