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
    this.asignarAsociacion();
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

  private void asignarAsociacion(){
    asociacionAsignada = RepositorioAsociaciones.getInstance().obtenerLaMasCercana(rescate);
  }

  public Asociacion getAsociacionAsignada() {
    return asociacionAsignada;
  }
}
